package com.kecsot.orchidsocial.repositories

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.support.annotation.RequiresPermission
import io.reactivex.Observable


class ImageRepository : ImageRepositoryInterface {


    @RequiresPermission(READ_EXTERNAL_STORAGE)
    @Throws(SecurityException::class)
    override fun uploadImage(filePath: String, ownerId: String): Observable<String> {

        return Observable.create { subscriber ->




        }
    }


}

/*
val storage = FirebaseStorage.getInstance()
                        val storageRFef = storage.reference
                        val islandRef = storageRef.child(next.images!![0]);

                        Glide.with(this /* context */)
                                .using(FirebaseImageLoader())
                                .load(islandRef)
                                .into(image);
 */

// Egyesével


/*
// Lista




//val path = Environment.getExternalStorageDirectory().toString()

/*
        var fOut: OutputStream? = null
        val counter = 0
        val file = File(path, "FitnessGirl.jpg") // the File to save , append increasing numeric counter to prevent files from getting overwritten.
        fOut = FileOutputStream(file)

        val pictureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kinder); // obtaining the Bitmap
        pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut) // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
        fOut!!.flush() // Not really required
        fOut!!.close() // do not forget to close the stream

 */
/*
if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    TODO("ERR")
} else {

    imageRepository.uploadImage("$path/FitnessGirl.jpg", "tom2")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { url ->
                        Log.d("teszt", "feltöltve.Url :" + url?.toString())
                        Toast.makeText(this@FullScreenImageActivity, url?.toString(), Toast.LENGTH_LONG).show()
                    },
                    { err ->
                        Log.d("teszt", "Error happened:" + err.localizedMessage)
                    }
            )

}


 */