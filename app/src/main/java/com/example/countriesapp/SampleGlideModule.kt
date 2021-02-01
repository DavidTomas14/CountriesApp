package com.example.countriesapp

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class SampleGlideModule: AppGlideModule() {
    @Override
    fun isManifestParsingEnables() = false
}