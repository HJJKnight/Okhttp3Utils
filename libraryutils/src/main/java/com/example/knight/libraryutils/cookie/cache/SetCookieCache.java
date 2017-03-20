package com.example.knight.libraryutils.cookie.cache;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Cookie;

/**
 * Created by knight on 2017/3/20.
 */

public class SetCookieCache implements CookieCache {

    private Set<IdentifiableCookie> cookies;

    @Override
    public void addAll(Collection<Cookie> cookies) {
        for (IdentifiableCookie cookie : IdentifiableCookie.decorateAll(cookies)) {
            this.cookies.remove(cookie);
            this.cookies.add(cookie);
        }
    }

    @Override
    public void clear() {
        cookies.clear();

    }

    @Override
    public Iterator<Cookie> iterator() {
        return new SetCookieCacheIterator();
    }

    private class SetCookieCacheIterator implements Iterator<Cookie>{

        private Iterator<IdentifiableCookie> iterator;

        public SetCookieCacheIterator(){
            iterator = cookies.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Cookie next() {
            return iterator.next().getCookie();
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }
}
