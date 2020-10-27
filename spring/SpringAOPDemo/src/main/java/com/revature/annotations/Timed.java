package com.revature.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // This annotation may only be applied on top of methods
public @interface Timed {

}
