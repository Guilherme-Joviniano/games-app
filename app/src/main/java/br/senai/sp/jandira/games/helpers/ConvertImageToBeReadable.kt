package br.senai.sp.jandira.games.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

fun getBitmapFromUri(imageUri: Uri?, context: Context):Bitmap {
    return MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
}

fun getBitmapFromByteArray(imageByteArray: ByteArray?): Bitmap {
    val arrayInputStream = ByteArrayInputStream(imageByteArray)
    return BitmapFactory.decodeStream(arrayInputStream)
}
fun getByteArrayFromBitmap(bitmap: Bitmap?): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.PNG, 90, stream)
    return stream.toByteArray()
}