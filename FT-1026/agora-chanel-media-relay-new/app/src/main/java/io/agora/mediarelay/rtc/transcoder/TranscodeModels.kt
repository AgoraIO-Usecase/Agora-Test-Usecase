package io.agora.mediarelay.rtc.transcoder

import android.util.SparseIntArray
import androidx.annotation.Size
import io.agora.rtc2.video.VideoEncoderConfiguration

data class TransInputItem(
   val uid: Int,
   val x: Int,
   val y: Int,
   val width: Int,
   val height: Int,
) {}

data class TranscodeSetting(
   val rtcChannel: String,
   val cdnURL: String,
   val fps: Int,
   val bitrate: Int,
   val width: Int,
   val height: Int,
   val inputItems: List<TransInputItem>
) {

   companion object {
      /**旁路推流转码1v1*/
      fun liveTranscoding(channel: String, cdn: String, @Size(min = 1) vararg uids: Int): TranscodeSetting {
         // 竖屏
         val fullWidth = VideoEncoderConfiguration.VD_1920x1080.height
         val fullHeight = VideoEncoderConfiguration.VD_1920x1080.width
         val inputItems = mutableListOf<TransInputItem>()
         // 分配主播的画面布局。
         var totalX = 0
         var totalY = 0
         if (uids.size > 1) {
            totalY = fullHeight / (uids.size * uids.size)
         }
         uids.forEach {
            val item = TransInputItem(
               uid = it,
               x = totalX,
               y = totalY,
               width = fullWidth / uids.size,
               height = fullHeight / uids.size
            )
            inputItems.add(item)
            totalX += fullWidth / uids.size
         }
         // 旁路推流，默认1080p24fps分辨率。
         return TranscodeSetting(
            channel,
            cdn,
            VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24.value,
            3072,
            fullWidth,
            fullHeight,
            inputItems
         )
      }

      /**旁路推流转码1vn*/
      fun liveTranscodingMulti(channel: String, cdn: String, videoUids: SparseIntArray): TranscodeSetting {
         // 竖屏
         val fullWidth = VideoEncoderConfiguration.VD_1920x1080.height
         val fullHeight = VideoEncoderConfiguration.VD_1920x1080.width
         val inputItems = mutableListOf<TransInputItem>()
         // 分配主播的画面布局。
         val startY = ((fullHeight - fullWidth) * 0.5).toInt()
         val singleSize = fullWidth / 4
         var transcodingX = 0
         var transcodingY = 0
         for (i in 0 until videoUids.size()) {
            val videoUid = videoUids[i]
            if (videoUid ==-1) continue
            transcodingX = singleSize * (i % 4)
            transcodingY = startY + singleSize * (i / 4)
            val item = TransInputItem(
               uid = videoUid,
               x = transcodingX,
               y = transcodingY,
               width = singleSize,
               height = singleSize
            )
            inputItems.add(item)
         }
         // 旁路推流，默认1080p24fps分辨率。
         return TranscodeSetting(
            channel,
            cdn,
            VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15.value,
            3072,
            fullWidth,
            fullHeight,
            inputItems
         )
      }

      /**旁路推流转码1v2*/
      fun liveTranscoding3(channel: String, cdn: String, videoUids: SparseIntArray): TranscodeSetting {
         // 竖屏
         val fullWidth = VideoEncoderConfiguration.VD_1920x1080.height
         val fullHeight = VideoEncoderConfiguration.VD_1920x1080.width
         val inputItems = mutableListOf<TransInputItem>()
         // 分配主播的画面布局。
         val startY = ((fullHeight - fullWidth) * 0.5).toInt()
         val unitSize = (fullWidth * 0.5).toInt()
         var singleWidth = 0
         var singleHeight = 0
         var transcodingX = 0
         var transcodingY = 0
         for (i in 0 until videoUids.size()) {
            val videoUid = videoUids[i]
            if (videoUid ==-1) continue
            if (i == 0) {
               singleWidth = unitSize
               singleHeight = unitSize * 2
               transcodingX = 0
               transcodingY = startY
            } else if (i == 1) {
               singleWidth = unitSize
               singleHeight = unitSize
               transcodingX = unitSize
               transcodingY = startY
            } else if (i == 2) {
               singleWidth = unitSize
               singleHeight = unitSize
               transcodingX = unitSize
               transcodingY = startY + unitSize
            }
            val item = TransInputItem(
               uid = videoUid,
               x = transcodingX,
               y = transcodingY,
               width = singleWidth,
               height = singleHeight
            )
            inputItems.add(item)
         }
         // 旁路推流，默认1080p24fps分辨率。
         return TranscodeSetting(
            channel,
            cdn,
            VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15.value,
            3072,
            fullWidth,
            fullHeight,
            inputItems
         )
      }
      /**旁路推流转码1v3*/
      fun liveTranscoding4(channel: String, cdn: String, videoUids: SparseIntArray): TranscodeSetting {
         // 竖屏
         val fullWidth = VideoEncoderConfiguration.VD_1920x1080.height
         val fullHeight = VideoEncoderConfiguration.VD_1920x1080.width
         val inputItems = mutableListOf<TransInputItem>()
         // 分配主播的画面布局。
         val startY = ((fullHeight - fullWidth) * 0.5).toInt()
         val singleSize = fullWidth / 2
         var transcodingX = 0
         var transcodingY = 0
         for (i in 0 until videoUids.size()) {
            val videoUid = videoUids[i]
            if (videoUid ==-1) continue
            transcodingX = singleSize * (i % 2)
            transcodingY = startY + singleSize * (i / 2)
            val item = TransInputItem(
               uid = videoUid,
               x = transcodingX,
               y = transcodingY,
               width = singleSize,
               height = singleSize
            )
            inputItems.add(item)
         }
         // 旁路推流，默认1080p24fps分辨率。
         return TranscodeSetting(
            channel,
            cdn,
            VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15.value,
            3072,
            fullWidth,
            fullHeight,
            inputItems
         )
      }
   }
}