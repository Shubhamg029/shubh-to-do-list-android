package com.shubham.todolist.base

import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

open class BaseResponse(
  var taskcode: Int? = null,
  var status: Int? = null,
  var message: String? = null,
  var error: Throwable? = null,
  var stringResponse: String? = null,
  var arrayResponse: Array<*>? = null,
  var anyResponse: Any? = null,
) : Serializable {

  //Deprecate
  fun message(): String {
    return errorFlowMessage() ?: "Something went wrong!"
  }

  //Deprecate
  fun errorMessage(): String? {
    return errorFlowMessage()
  }

  //Deprecate
  fun errorNMessage(): String? {
    return errorFlowMessage()
  }

  //Deprecate
  fun errorIPMessage(): String? {
    return errorFlowMessage()
  }

  //TODO New Error message filter (Use this function get error message)

  fun errorFlowMessage(): String? {
    val message = message
    return try {
      val jsonObj = JSONObject(message)
      val error: JSONArray? = jsonObj.getJSONArray("errors")
      val jsonResult = if (error?.length() ?: 0 >= 1) error?.get(0) as? JSONObject else null
      return jsonResult?.getStringValue("message") ?: jsonObj.getStringValue("Message") ?: jsonObj.getStringValue("EXCEPTION") ?: messageN()
    } catch (ex: Exception) {
      val messageN = messageN()
      if (messageN.isNullOrEmpty().not()) messageN else null
    }
  }

  fun messageN(): String? {
    val message = message
    return try {
      val jsonObj = JSONObject(message)
      jsonObj.getStringValue("Message") ?: jsonObj.getStringValue("message") ?: errorMessageN()
    } catch (ex: Exception) {
      errorMessageN()
    }
  }

  fun errorMessageN(): String? {
    val message = message
    return try {
      val jsonObj = JSONObject(message)
      val error: JSONObject? = jsonObj.getJSONObject("Error")
      error?.getStringValue("ErrorDescription") ?: error?.getStringValue("errorDescription") ?: errorNMessageN()
    } catch (ex: Exception) {
      errorNMessageN()
    }
  }

  fun errorNMessageN(): String? {
    val message = message
    return try {
      val jsonObj = JSONObject(message)
      val error: JSONObject? = jsonObj.getJSONObject("Error")?.getJSONObject("ErrorList")
      return error?.getStringValue("EXCEPTION") ?: errorIPMessageN()
    } catch (ex: Exception) {
      errorIPMessageN()
    }
  }

  fun errorIPMessageN(): String? {
    val message = message
    return try {
      val jsonObj = JSONObject(message)
      val error: JSONObject? = jsonObj.getJSONObject("Error")?.getJSONObject("ErrorList")
      return error?.getStringValue("INVALID PARAMETERS") ?: message
    } catch (ex: Exception) {
      message
    }
  }

  fun JSONObject.getStringValue(name: String): String? {
    return try {
      this.getString(name)
    } catch (e: Exception) {
      null
    }
  }


  fun isSuccess(): Boolean {
    return status == 200 || status == 201 || status == 202 || status == 204
  }
}