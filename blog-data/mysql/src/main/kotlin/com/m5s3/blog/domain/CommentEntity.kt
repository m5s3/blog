package com.m5s3.blog.domain

import com.m5s3.blog.domain.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name="comment")
class CommentEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = 0,

    @ManyToOne(optional = false)
    @JoinColumn(name = "blogId")
    var blogEntity: BlogEntity,

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    var memberEntity: MemberEntity,

    @Column(length = 500, nullable = false)
    var content: String,

    @Column(updatable = false)
    var parentCommentId: Long? = null,

    @OrderBy("created_at ASC")
    @OneToMany(mappedBy = "parentCommentId", cascade = [CascadeType.ALL])
    var childComments: MutableSet<CommentEntity> = mutableSetOf()
) : BaseEntity() {
    fun addChildComments(child: CommentEntity) {
        child.parentCommentId = this.id
        this.childComments.add(child)
    }
}