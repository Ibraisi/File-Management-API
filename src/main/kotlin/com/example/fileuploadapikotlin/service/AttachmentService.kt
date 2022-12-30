package com.example.fileuploadapikotlin.service

import com.example.fileuploadapikotlin.model.Attachment
import com.example.fileuploadapikotlin.repository.AttachmentRepository
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile

@Service
class AttachmentService(private val attachmentRepository: AttachmentRepository){

     fun saveAttachment(file: MultipartFile): Attachment {
        val fileName = StringUtils.cleanPath(file.originalFilename!!)
        try {
            if (fileName.contains("..")) {
                throw Exception("Filename contains invalid path sequence $fileName")
            }

            val attachment = Attachment(fileName, file.contentType, file.bytes)
            return attachmentRepository.save(attachment)
        } catch (e: Exception) {
            throw Exception("Could not save File: $fileName")
        }
    }

     fun getAttachment(fileId: String): Attachment {
        return attachmentRepository.findById(fileId).orElseThrow { Exception("File not found with Id: $fileId") }
    }
}
