package in.naishe.algo.utils;


import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ExpressionStatementVisitor extends ASTVisitor{

    List<SimpleName> list = new List<SimpleName>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<SimpleName> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(SimpleName simpleName) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends SimpleName> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends SimpleName> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public SimpleName get(int index) {
            return null;
        }

        @Override
        public SimpleName set(int index, SimpleName element) {
            return null;
        }

        @Override
        public void add(int index, SimpleName element) {

        }

        @Override
        public SimpleName remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<SimpleName> listIterator() {
            return null;
        }

        @Override
        public ListIterator<SimpleName> listIterator(int index) {
            return null;
        }

        @Override
        public List<SimpleName> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    @Override
    public boolean visit(SimpleName simpleName) {
        System.out.println("inside ESV: "+simpleName);
        list.add(simpleName);
        System.out.println("----SIZE-----"+list.size());
        System.out.println("----First-----"+list.get(0));
        return super.visit(simpleName);
    }

    @Override
    public void endVisit(SimpleName simpleName) {
        super.endVisit(simpleName);

    }

    public List<SimpleName> getSimpleNameList() {
        return list;
    }
}