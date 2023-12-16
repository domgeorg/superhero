package com.georgiopoulos.core.data.repository.home

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkPager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabasePager
