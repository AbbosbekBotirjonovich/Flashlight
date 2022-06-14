package mobiler.abbosbek.flashlight

import android.content.Context
import android.graphics.Color
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mobiler.abbosbek.flashlight.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var flashSwitch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgSwitch.setOnClickListener{
            flashSwitch = !flashSwitch

            if (flashSwitch){
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        val camManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                        camManager.setTorchMode(camManager.cameraIdList[0],true)
                    }else{
                        val camera = Camera.open()
                        val params = camera.parameters
                        params.flashMode = Camera.Parameters.FLASH_MODE_TORCH
                        camera.parameters = params
                        camera.startPreview()
                    }
                    binding.tvTitle.setTextColor(Color.GREEN)
                    binding.imgSwitch.setImageResource(R.drawable.ic_power_on)
                }catch (e : Exception){

                }
            }else{

                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        val camManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                        camManager.setTorchMode(camManager.cameraIdList[0],false)
                    }else{
                        val camera = Camera.open()
                        val params = camera.parameters
                        params.flashMode = Camera.Parameters.FLASH_MODE_OFF
                        camera.parameters = params
                        camera.startPreview()
                    }
                    binding.tvTitle.setTextColor(Color.RED)
                    binding.imgSwitch.setImageResource(R.drawable.ic_power_off)
                }catch (e : Exception){

                }
            }
        }
    }
}