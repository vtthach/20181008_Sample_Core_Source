package com.innovation.rain.feature.rica.poa.scan.injection

import android.content.Context
import com.innovation.rain.app.properties.BuildInProperties
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraControllerImpl
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.Provides

@Module
class ScanPOAControllerModule {
    @Provides
    @PerView
    fun provideController(context: Context, properties: BuildInProperties): CameraController {
        return CameraControllerImpl(context, properties.zoomRatioPOA)
    }
}