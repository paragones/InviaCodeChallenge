package com.inviacodechallenge.parag.models

class Pagination {
    private var links = arrayOf<Link?>()

    val firstLink: Link?
        get() = getLinkByRel(Link.Rel.FIRST)

    val prevLink: Link?
        get() = getLinkByRel(Link.Rel.PREV)

    val nextLink: Link?
        get() = getLinkByRel(Link.Rel.NEXT)

    val lastLink: Link?
        get() = getLinkByRel(Link.Rel.LAST)

    fun hasPrev(): Boolean {
        return getLinkByRel(Link.Rel.PREV) != null
    }

    operator fun hasNext(): Boolean {
        val nextLink = getLinkByRel(Link.Rel.NEXT)
        return nextLink != null && nextLink.page > 0
    }

    fun hasLast(): Boolean {
        return getLinkByRel(Link.Rel.LAST) != null
    }

    private fun getLinkByRel(rel: Link.Rel): Link? {
        for (link in links) {
            if (link?.rel === rel) {
                return link
            }
        }
        return null
    }

    constructor() {}

    constructor(links: Array<Link?>) {
        this.links = links
    }
}