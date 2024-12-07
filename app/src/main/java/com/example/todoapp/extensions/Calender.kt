package com.example.todoapp.extensions

import java.util.Calendar


fun Calendar.cleartime(){
    this.set(Calendar.HOUR_OF_DAY,0)
    this.set(Calendar.MINUTE,0)
    this.set(Calendar.SECOND,0)
    this.set(Calendar.MILLISECOND,0)


}