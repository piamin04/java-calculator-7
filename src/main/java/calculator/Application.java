package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        //입력받기
        System.out.print("input string");
        String input = Console.readLine();

        // 4. 예외처리(입력값 검증)
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("empty");
        }

        //기본 구분자
        String[] delimiters = {",", ":"};

        //2.커스텀 구분자 확인 (\n은 커스텀 구분자로 생성할 수 없음)
        // -> 커스텀 구분자를 2개이상 생성하는 경우의 코드는 아직 안짬
        // -> 얘는 밑에 코드를 while문안에 넣어놓고 조건을 문장의 첫 부분이 \가 아닐때까지로 하면되지않을까..?
        //그냥 커스텀 구분자는 입력 하나에 하나만 있다고 해야지...ㅎㅅㅎ
        String newdelimiter="";
        int newdelimitersize=0;
        if(input.startsWith("//")){
            int customendIndex=input.indexOf("\\n");
            if(customendIndex==-1){
                throw new IllegalArgumentException("Error");
            }
            newdelimiter = input.substring(2, customendIndex); // "//" 이후와 "\n" 사이 추출
            String[] newDelimiters = new String[delimiters.length + 1]; //배열 복사를 위한 새로운 배열 생성
            System.arraycopy(delimiters, 0, newDelimiters, 0, delimiters.length);
            newDelimiters[newDelimiters.length - 1] = newdelimiter;
            delimiters = newDelimiters; // 새 구분자로 업데이트
            input = input.substring(customendIndex + 2); // "\n" 이후 문자열만 남김
        }


        //split함수에 들어갈 문자열 생성
        String delimiterRegex="[";
        delimiterRegex+=delimiters[0];
        for(int i=1;i<delimiters.length;i++){
            delimiterRegex+="|";
            delimiterRegex+=delimiters[i];
        }
        delimiterRegex+="]";

        // 구분자를 기준으로 문자열 나누기
        String[] numbers = input.split(delimiterRegex);

        // 빈 문자열 제거   if(delimiters.length>2&&delimiters[2].startsWith(":|,"))
        if(delimiters.length>2){
            numbers = Arrays.stream(numbers)
                    .filter(number -> !number.trim().isEmpty())
                    .toArray(String[]::new);
        }

        //String [] numbers = input.split("[,|:]");
        int numberslenth =numbers.length;
        //System.out.println(numberslenth);

        //3. 숫자 더하기
        //문자열 숫자로 변환
        int sum=0;
        for(int j=0;j<numberslenth;j++){
            int real =Integer.parseInt(numbers[j]);
            sum+=real;
        }

        System.out.println("sum = "+sum);
    }
}