package com.akarbowy.cakelisttest.injection

import android.app.Activity


internal interface DaggerComponentProvider {

    val component: AppComponent
}

internal val Activity.injector get() = (application as DaggerComponentProvider).component