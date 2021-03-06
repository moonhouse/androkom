/**! -*- Mode: Java; c-basic-offset: 4 -*-
 *
 * Copyright (c) 1999 by Rasmus Sten <rasmus@sno.pp.se>
 *
 */
package org.lysator.lattekom;

import java.util.*;

class TextStatCache {
    final static boolean DEBUG = Boolean.getBoolean("lattekom.caches.debug");
    Hashtable<Integer, TextStat> hash;

    public TextStatCache() {
        hash = new Hashtable<Integer, TextStat>();
    }

    public synchronized void add(TextStat t) {
        if (t.getNo() == -1)
            return; // throw(new TextNumberException("Text has no number"));

        if (DEBUG)
            Debug.println("TextStatCache: adding " + t.getNo());
        if (hash.put(new Integer(t.getNo()), t) != null) {
            if (DEBUG) {
                Debug.println("TextStatCache: " + "replacing text-stat #"
                        + t.getNo() + " in cache");
            }
        }
    }

    public void clear() {
        hash.clear();
    }

    public boolean contains(int textNo) {
        return hash.containsKey(new Integer(textNo));
    }

    public synchronized boolean remove(int textNo) {
        return hash.remove(new Integer(textNo)) != null;
    }

    public TextStat get(int textNo) {
        TextStat t = (TextStat) hash.get(new Integer(textNo));
        if (t != null && DEBUG)
            Debug.println("TextStatCache: returning " + t);

        return t;
    }
}
