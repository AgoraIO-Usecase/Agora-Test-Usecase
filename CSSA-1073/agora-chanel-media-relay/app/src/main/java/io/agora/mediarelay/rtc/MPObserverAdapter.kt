package io.agora.mediarelay.rtc

import io.agora.mediaplayer.Constants
import io.agora.mediaplayer.IMediaPlayerObserver
import io.agora.mediaplayer.data.PlayerUpdatedInfo
import io.agora.mediaplayer.data.SrcInfo

open class MPObserverAdapter : IMediaPlayerObserver {
    override fun onPlayerStateChanged(state: Constants.MediaPlayerState?, error: Constants.MediaPlayerError?) {
    }

    override fun onPositionChanged(position_ms: Long) {
    }

    override fun onPlayerEvent(eventCode: Constants.MediaPlayerEvent?, elapsedTime: Long, message: String?) {
    }

    override fun onMetaData(type: Constants.MediaPlayerMetadataType?, data: ByteArray?) {
    }

    override fun onPlayBufferUpdated(playCachedBuffer: Long) {
    }

    override fun onPreloadEvent(src: String?, event: Constants.MediaPlayerPreloadEvent?) {
    }

    override fun onAgoraCDNTokenWillExpire() {
    }

    override fun onPlayerSrcInfoChanged(from: SrcInfo?, to: SrcInfo?) {
    }

    override fun onPlayerInfoUpdated(info: PlayerUpdatedInfo?) {
    }

    override fun onAudioVolumeIndication(volume: Int) {
    }
}