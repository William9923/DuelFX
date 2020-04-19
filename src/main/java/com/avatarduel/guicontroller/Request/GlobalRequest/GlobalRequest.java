package com.avatarduel.guicontroller.Request.GlobalRequest;

import com.avatarduel.guicontroller.Request.Request;

/**
 * A marker class, used to denote that a request is done globally, means that
 * the one accepting the request does not care who sends it, it will execute
 * the request
 * @author G10-K03-CardGameOOP
 */
public abstract class GlobalRequest implements Request {
}
