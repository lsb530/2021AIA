class Card {
    int kind;
    int number;
    Card(int kind, int number) {
        this.kind = kind;
        this.number = number;
    }
    Card() {
        this(1,1);
    }
    public String toString() {
        return String.format("[%d, %d]", kind, number);
    }
}

public class MainTest {
    // 아래의 rankCheck메서드는 주어진 Card배열의 값을 읽어서
    // 카드의 랭크를 반환한다. 이 메서드를 완성하시오.
    // 1. "STRAIGHT" - 카드의 숫자가 연속인 경우, 1,2,3,4,5 또는 5,6,7,8,9
    // 2. "FLUSH" - 카드의 무늬(KIND)가 모두 같은 경우
    // 3. "STRAIGHT FLUSH"
    // 4. "1 PAIR" - 같은 카드 2장 1개
    // 5. "2 PAIR" - 같은 카드 2장 2개
    // 6. "THREE CARD" - 같은 카드 3장 1,1,1,7,2
    // 7. "FOUR CARD" - 같은 카드 4장 4,4,4,4,5
    // 8. "FULL HOUSE" - THREE CARD & ONE PAIR 1,1,1,5,5
    public static String rankCheck(Card[] cArr) {
        //1, 2, 3번 문제
        String answer = "";
        int check_number = cArr[0].number;
        int check_kind = cArr[0].kind;
        int pair = 0;
        boolean straight = false; boolean flush = false;
        // 1번 처리 for문
        // 1. "STRAIGHT" - 카드의 숫자가 연속인 경우, 1,2,3,4,5 또는 5,6,7,8,9
        for(int i=1; i<cArr.length; i++) {
            int number = cArr[i].number;
            if((number-check_number)==1) {
                straight = true;
            } else if((number-check_number)!=1) {
                straight = false;
                break;
            }
            check_number++;
        }
        // 2번 처리 for문
        // 2. "FLUSH" - 카드의 무늬(KIND)가 모두 같은 경우
        for(int i=1; i<cArr.length; i++) {
            int kind = cArr[i].kind;
            if(kind==check_kind) {
                flush = true;
            } else {
                flush = false;
                break;
            }
        }
        //4, 5, 6, 7, 8처리
        // 카운팅을 한다
        // 1. 카운팅을 위한 배열을 설정
        int[] cntArr = new int[14]; // 0~13
        int continues = 0;
        // 2. 카운팅
        for(int i=0; i<cArr.length; i++) {
            cntArr[cArr[i].number]++;
        }
        // 3. 카운팅 결과를 확인
        int pairCnt = 0; boolean isFourCard = false; boolean isThreeCard = false;
        for(int i=0; i<cntArr.length; i++) {
            if(cntArr[i]==4) {
                isFourCard = true;
            }
            if(cntArr[i]==3) {
                isThreeCard = true;
            }
            if(cntArr[i]==2) {
                pairCnt++;
            }
        }
        // 답 처리
        if(straight) {
            if(flush) {
                answer = "STRAIGHT FLUSH";
            } else {
                answer = "STRAIGHT";
            }
        }
        else if(flush) answer = "FLUSH";
        else if(pairCnt==1){
            if(isThreeCard) {
                answer = "FULL HOUSE";
            }
            else answer = "1 PAIR";
        }
        else if(pairCnt==2) answer = "2 PAIR";
        else if(isFourCard) answer = "FOUR CARD";
        else if(isThreeCard) answer = "THREE CARD";
        System.out.println(answer);
        return answer;
    }
}
