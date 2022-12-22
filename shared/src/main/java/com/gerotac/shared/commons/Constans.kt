package com.gerotac.shared.commons

object Constant {
    const val URL: String = "https://parces.gerotac.com/api/v1/"
    //const val URL: String = "https://parce-api-app.gerotac.com/api/v1/"

    //TODO https://parces.gerotac.com/ RUTA lOCAL PARA VER LOS ARCHIVOS
    const val URL_VIEW_LOCAL_FILE: String = "https://parces.gerotac.com/"
    //TODO https://parce-api-app.gerotac.com/ RUTA PRODUCCION PARA VER LOS ARCHIVOS
    const val URL_VIEW_PRODUCTION_FILE: String = "https://parce-api-app.gerotac.com/"

    //HEADERS
    const val AUTHORIZATION: String = "Authorization"
    const val ACCEPT: String = "Accept"
    const val CONTENT_TYPE: String = "Content-Type"
    const val ACCEPT_ENCODING: String = "Accept-Encoding"
    const val DATA_STORE_FILE_NAME = "user_store.proto"
}
