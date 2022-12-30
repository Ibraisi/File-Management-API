package com.example.fileuploadapikotlin.controller

import com.example.fileuploadapikotlin.model.Attachment
import com.example.fileuploadapikotlin.model.ResponseData
import com.example.fileuploadapikotlin.service.AttachmentService
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class AttachmentController(private val attachmentService: AttachmentService) {

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseData {
        var attachment: Attachment? = null
        var downloadURl = ""
        attachment = attachmentService.saveAttachment(file)
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/download/")
            .path(attachment.id!!)
            .toUriString()

        return ResponseData(attachment.fileName!!,
            downloadURl,
            file.contentType!!,
            file.size)
    }

    @GetMapping("/download/{fileId}")
    fun downloadFile(@PathVariable fileId: String): ResponseEntity<Resource> {
        val attachment: Attachment? = attachmentService.getAttachment(fileId)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(attachment!!.fileType!!))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + attachment!!.fileName
                        + "\"")
            .body(ByteArrayResource(attachment.data!!))
    }
}
