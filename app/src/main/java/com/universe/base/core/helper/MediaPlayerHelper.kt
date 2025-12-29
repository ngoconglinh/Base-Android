package com.universe.base.core.helper

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MediaPlayerHelper(private val context: Context) {
    private var exoPlayer: ExoPlayer? = null
    private var attachedPlayerView: PlayerView? = null
    private var listener: MediaPlayerListener? = null

    private val progressHandler = Handler(Looper.getMainLooper())
    private var progressUpdateInterval = 100L
    private var isTrackingProgress = false

    private val progressRunnable = object : Runnable {
        override fun run() {
            exoPlayer?.let { player ->
                val position = player.currentPosition
                val duration = player.duration

                listener?.onProgressUpdate(
                    currentPosition = position,
                    duration = if (duration > 0) duration else 0L,
                )
            }

            if (isTrackingProgress) {
                progressHandler.postDelayed(this, progressUpdateInterval)
            }
        }
    }

    fun initialize() {
        if (exoPlayer != null) return

        exoPlayer = ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_BUFFERING -> {
                            listener?.onLoading()
                        }

                        Player.STATE_READY -> {
                            listener?.onReady()
                        }

                        Player.STATE_ENDED -> {
                            listener?.onEnded()
                        }

                        Player.STATE_IDLE -> {
                            // No action needed
                        }
                    }
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    listener?.onPlayingChanged(isPlaying)

                    // Start/stop progress tracking based on playing state
                    if (isPlaying) {
                        startProgressTracking()
                    } else {
                        stopProgressTracking()
                    }
                }

                override fun onPlayerError(error: PlaybackException) {
                    stopProgressTracking()
                    listener?.onError(error)
                }
            })
        }
    }

    fun attachTo(playerView: PlayerView) {
        attachedPlayerView?.player = null
        attachedPlayerView = playerView
        attachedPlayerView?.player = exoPlayer
    }

    fun preparePlayback(url: String, autoPlay: Boolean = true, isLoop: Boolean = false) {
        if (exoPlayer == null) {
            initialize()
        }

        exoPlayer?.apply {
            stop()
            clearMediaItems()

            val mediaItem = MediaItem.fromUri(url)
            setMediaItem(mediaItem)
            repeatMode = if (isLoop) Player.REPEAT_MODE_ONE else Player.REPEAT_MODE_OFF
            playWhenReady = autoPlay
            prepare()
        }
    }

    fun isPlaying(): Boolean = exoPlayer?.isPlaying ?: false

    fun getDuration(): Long = exoPlayer?.duration?.takeIf { it > 0 } ?: 0L

    fun getCurrentPosition(): Long = exoPlayer?.currentPosition ?: 0L

    fun play() {
        exoPlayer?.apply {
            if (playbackState == Player.STATE_ENDED) {
                seekTo(0)
            }
            play()
        }
    }

    fun pause() {
        exoPlayer?.pause()
    }

    fun stop() {
        stopProgressTracking()
        exoPlayer?.apply {
            stop()
            clearMediaItems()
        }
    }

    fun seekTo(positionMs: Long) {
        exoPlayer?.seekTo(positionMs)
    }

    fun setPlaybackSpeed(speed: Float) {
        exoPlayer?.setPlaybackSpeed(speed)
    }

    fun release() {
        stopProgressTracking()
        exoPlayer?.release()
        exoPlayer = null
        attachedPlayerView?.player = null
        attachedPlayerView = null
        listener = null
    }

    fun setMediaPlayerListener(listener: MediaPlayerListener) {
        this.listener = listener
    }

    private fun startProgressTracking() {
        if (!isTrackingProgress) {
            isTrackingProgress = true
            progressHandler.post(progressRunnable)
        }
    }

    private fun stopProgressTracking() {
        isTrackingProgress = false
        progressHandler.removeCallbacks(progressRunnable)
    }

    interface MediaPlayerListener {
        fun onLoading() {}
        fun onReady() {}
        fun onPlayingChanged(isPlaying: Boolean) {}
        fun onEnded() {}
        fun onError(error: PlaybackException) {}
        fun onProgressUpdate(currentPosition: Long, duration: Long) {}
    }
}