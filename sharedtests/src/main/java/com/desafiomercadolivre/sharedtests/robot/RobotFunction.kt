package com.desafiomercadolivre.sharedtests.robot

import com.desafiomercadolivre.architecture.extensions.getFromKoin

inline fun <reified Robot : Any, Return> robot(func: Robot.() -> Return) =
    getFromKoin<Robot>().func()