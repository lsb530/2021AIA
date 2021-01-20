import org.junit.Test;
import sun.nio.cs.ext.MacTurkish;
import java.util.Vector;
import static org.junit.Assert.*;

class MyVector {
    private Object[] objArr = new Object[0];
// Object[] objArr = null; // NullpointerException발생 가능
    private int size = 0;
    MyVector() {
        this(10);
    }
    MyVector(int capacity){
        if(capacity <0)
            throw new IllegalArgumentException("Illegal Capacity:"+capacity);
        objArr = new Object[capacity];
    }
// objArr에 있는 모든 객체를 삭제한다.
    void clear() {
// 1. objArr의 모든 요소에 null을 저장
        for (int i = 0; i < size(); i++) {
            objArr[i] = null;
        }
// 2. size의 값을 0으로 변경
        size = 0;
    }
// objArr에 저장된 객체의 개수
    int size() {
        return size;
    }
// objArr의 길이
    int capacity() {
        return objArr.length;
    }
// objArr이 비었는지 알려주는 메서드
    boolean isEmpty() {
// 언제 비었다고 하지?
        return size == 0;
    }
// obj를 objArr에 추가
    boolean add(Object obj){
// 1. 추가할 공간이 있는지 확인
        ensureCapacity(size+1);
// 2. 추가. 어디에? 맨뒤에.
        objArr[size] = obj;
        size++;
        return true;
    }
// index에 있는 객체를 삭제하고, 삭제된 객체를 반환
    Object remove(int index) {
// 1. 유효성 검사. 0 <= index < size
        if(index<0 && index >= size)
            throw new ArrayIndexOutOfBoundsException("Array index out of range:"+index);
// 삭제될 객체를 따로 저장
        Object old = objArr[index];
// 1. index이후의 요소들을 하나씩 위로 복사
        System.arraycopy(objArr, index+1, objArr, index, size-index-1);
// 2. 마지막 요소를 null로 저장
        objArr[size-1] = null;
// 3. size를 1 감소
        size--;
        return old;
    }
    public String toString() {
        String tmp = "[";
        final int SIZE = size();
        for (int i = 0; i < SIZE; i++) {
            tmp += objArr[i] + ", ";
        }
        return tmp + "]";
    }
    boolean remove(Object obj) {
        return false;
    }
// obj가 objArr의 몇번째 index에 저장되어 있는지 찾는다.
    int indexOf(Object obj) {
        final int SIZE = size();
        int index = -1;
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("i=%d, obj=%s%n", i, obj.toString());
            if(obj==null) {
                if(objArr[i]==obj) {
                    return i;
                }
            } else {
                if(obj.equals(objArr[i])) {
                    return i;
                }
            }
        }
        return index;
    }
    void ensureCapacity(int minCapacity) {
// 공간이 부족하면
        if(capacity() < minCapacity) {
// 공간을 확보해준다.
// 1. 더 큰 새로운 배열을 만들고
            Object[] tmpArr = new Object[minCapacity*2];
// 2. 기존의 내용을 새로운 배열에 복사하고
            System.arraycopy(objArr,0, tmpArr,0, size);
// 3. 배열의 참조를 변경
            objArr = tmpArr;
        }
// 공간이 부족하지 않으면 - 아무일도 안하면 된다.
    }
}

public class MainTest {
    @Test
    public void removeTest1(){
        Vector v = new Vector();
        v.add("123");
        v.remove(v.size()+1);
    }
    @Test
    public void indexOfTest(){
        MyVector v = new MyVector(100);
        v.add("abc");
        v.add("123");
        v.add(null);
        v.add("zzz");
        assertTrue(v.indexOf("abc")==0);
        assertTrue(v.indexOf("xxx")==-1);
    }
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void capacityTest1(){
        MyVector v = new MyVector(100);
    }
    @Test
    public void capacityTest2() {
        MyVector v = new MyVector(1000);
        assertTrue(v.capacity()==1000);
        v = new MyVector();
        assertTrue(v.capacity()==10);
    }
    @Test
    public void sizeTest() {
        MyVector v = new MyVector();
        assertTrue(v.size()==0);
        v.add(null);
        assertTrue(v.size()==1);
        for (int i = 0; i < 100; i++) {
            v.add(i+"");
        }
        v.clear();
        System.out.println("v.size()="+v.size());
        assertTrue(v.isEmpty());
    }
    @Test
    public void addTest() {
        MyVector v = new MyVector();
        for (int i = 0; i <1_000_000 ; i++) {
            v.add("111");
        }
        assertTrue(v.size()==1_000_000);
        System.out.println("capacity="+v.capacity());
    }
}