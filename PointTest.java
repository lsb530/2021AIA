class Point3D extends Point {

    int z;

    Point3D(int x, int y, int z){
        super(x,y); // Point(x,y);
        this.z = z;
    }

    Point3D(){
        this(1,1,1); // Point3D(1,1,1);
    }

    public String toString() {
        return super.toString() + ",z="+z;
    }
}


// Q1. 두개의 정수 x, y를 저장하기 위한 인스턴스 변수가 선언된 Point클래스를 정의하시오.

class Point {

// 인스턴스 변수는 참조변수.iv
    int x; // this.x
    int y; // this.y

// Q4. Point클래스에 x, y를 매개변수로 하는 생성자와 기본 생성자(x, y를 모두 1로 초기화)를 추가하시오.

// 그 다음에 PointTest클래스에서 테스트 하시오.
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Point() {
        this(1,1);
    }

// Q3. Point클래스에 toString()을 오버라이딩해서 x와 y의 값을 포함한 문자열을 반환하도록 하시오.
// PointTest클래스에서 Point객체를 생성한 다음, x와 y의 값을 3과 5로 초기화 하시오.
// 그리고 toString()을 호출해서 x와 y의 값을 출력하시오.
// Object의 toString()을 오버라이딩
    public String toString(){
        return "x="+x+", y="+y;
    }

// Q5. Point클래스에 두점 사이의 거리를 double타입의 값으로 계산해서 반환하는 static메서드
// getDistance(Point p1, Point p2)를 추가하시오.
    static double getDistance(Point p1, Point p2){
        int a = p1.x - p2.x;
        int b = p1.y - p2.y;
        return Math.sqrt(a*a + b*b);
    }

// Q6. Point클래스에 두 점 사이의 거리를 double타입의 값으로 계산해서 반환하는 getDistance(Point p)를 추가하시오.
// 두 점? 점이 한개 인데? p2, 점, 나 자신, this
// p와 p2의 거리를 구해서 출력
// System.out.println(p.getDistance(p2));
    double getDistance(Point p2) {
        return Point.getDistance(this,p2);
    }
// Q7. Point클래스의 equals()를 다음과 같은 조건으로 오버라이딩 하고 테스트 하시오.
// - 비교하는 객체가 Point객체가 아니면 false를 반환
// - Point객체의 x와 y의 값이 같을 때만 true를 반환
// 주어진 객체(obj)가 나, 객체자신 this와 같은지 비교
// 두 점(obj, this)이 같은지 비교. 같으면 true, 다르면 false
    public boolean equals(Object obj){
// 두 점이 언제 같다고 하지? x, y가 같을 때
// obj에는 x가 없기 때문에, Point로 형변환
// 즉 리모콘 변경. 참조변수 형변환
// 참조변수 형변환 전에는 반드시 instanceof로 확인 필수
// 매개변수의 유효성 검사 필수
        if(!(obj instanceof Point)) return false;
        Point p = (Point)obj;
        return this.x==p.x && this.y==p.y;
    }
}
// Q8. Point클래스를 상속받고 z를 인스턴스 변수로 갖는 Point3D클래스를 작성하시오.
// Q9. Point3D클래스에 생성자 Point3D(int x, int y, int z)와 기본 생성자(x, y, z를 모두 1로 초기화)를 추가하시오.
// Q10. Point3D클래스의 toString()이 x, y, z의 값을 문자열로 반환하도록 오버라이딩 하시오.
// Q2. 앞서 정의한 Point클래스를 테스트하기 위한 PointTest클래스를 작성하고, Point객체를 생성하는 코드를 작성하시오.
public class PointTest {
    public static void main(String[] args) {
        Point p = new Point(1,1);
        Point p2 = new Point(1,1);
        // p와 p2가 같은지 비교.
        System.out.println(p.equals(p2));
        // p와 p2의 거리를 구해서 출력
        System.out.println(Point.getDistance(p, p2));
        // 인스턴스 메서드는 참조변수.메서드이름()으로 호출
        // p와 p2의 거리를 구해서 출력
        System.out.println(p.getDistance(p2));
        System.out.println(p);
        System.out.println(p2);
    }
}