package com.example.myapplication.ws

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WSListener : WebSocketListener () {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d("WS : ", "OPEN")
        webSocket.send("hello world")
        //webSocket.send("Hello, it's SSaurel !")
        //webSocket.send("What's up ?")
        //webSocket.send(ByteString.decodeHex("deadbeef"))
        //webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        output("Receiving : " + text!!)
    }

    /*
    override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
        Log.d("TEST RE3", "OKER")
        Log.d("RECEIVED WS : ", bytes!!.toString())
        print("NEW MESSAGE INC")
        //output("Receiving bytes : " + bytes!!.hex())
    }*/

    override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
        webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
        //output("Closing : $code / $reason")
    }

    companion object {
        private val NORMAL_CLOSURE_STATUS = 1000
    }

    private fun output(txt: String) {
        Log.v("WSS", txt)
    }
}
