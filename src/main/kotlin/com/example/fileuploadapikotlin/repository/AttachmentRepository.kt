package com.example.fileuploadapikotlin.repository

import com.example.fileuploadapikotlin.model.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttachmentRepository: JpaRepository<Attachment,String> {
}