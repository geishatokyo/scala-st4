package com.geishatokyo.scalast4

/**
 *
 * User: takeshita
 * Create: 12/04/05 1:29
 */

class TemplateNotFoundException(templateName : String) extends RuntimeException(templateName + " not found") {

}
