package io.agora.mediarelay.rtc

import androidx.annotation.Size
import io.agora.mediarelay.tools.LogTool
import io.agora.rtc2.live.LiveTranscoding
import io.agora.rtc2.video.VideoEncoderConfiguration

object AgoraRtcHelper {

    /**旁路推流转码*/
    fun liveTranscoding(@Size(min = 1) vararg uids: Int): LiveTranscoding {
        // 旁路推流，默认1080p24fps分辨率。
        val config = LiveTranscoding().apply {
            videoFramerate = VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24.value
            videoCodecType = LiveTranscoding.VideoCodecType.H265
            videoBitrate = 3072
            videoGop = 48
            width = VideoEncoderConfiguration.VD_1920x1080.height
            height = VideoEncoderConfiguration.VD_1920x1080.width
        }

        // 分配主播的画面布局。
        var totalX = 0
        var totalY = 0
        if (uids.size > 1) {
            totalY = config.height / (uids.size * uids.size)
        }
        uids.forEach {
            val user: LiveTranscoding.TranscodingUser = LiveTranscoding.TranscodingUser().apply {
                uid = it
                x = totalX
                y = totalY
                audioChannel = 0
            }
            user.width = config.width / uids.size
            user.height = config.height / uids.size
            totalX += config.width / uids.size
            config.addUser(user)
        }
        return config
    }
}