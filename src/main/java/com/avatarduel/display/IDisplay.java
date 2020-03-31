package com.avatarduel.display;

import com.avatarduel.model.Card;
import java.util.HashMap;

/*
Nanti ganti, jadi mungkin return hashmap terkait data buat si kartu (constructor controller kartu)
* */

public interface IDisplay {
    public HashMap<String, String> display(Card card);
}
