## MainActivity 설명

### lateinit 예약어

- 프로퍼티를 null 허용으로 선언하지 않고 프로퍼티 초기화를 미루는 방법
- 초깃값을 설정하지 않았으며 대신 lateinit을 명시
- lateinit는 var로 선언한 프로퍼티에만 사용할 수 있음



### duration
효과 시간 1/1000초 단위



### Thread

- 어떠한 버튼을 눌렀을 때 Main Thread 내부적으로 10초 이상이 걸리는 작업을 한다고 치면, 사용자는 그 일이 끝날 때까지 멈춰있는 화면만 보고 있어야 함

  

- 기본적인 방법

1. Thread 클래스를 상속 받는 하위 스레드 클래스 생성
2. 하위 스레드 내에 run( )을 오버라이드 한다. run( )은 Thread가 실행되면 수행되는 곳이다.
3. Thread 객체를 생성해주고 start( ) 메소드로 Tread의 run( ) 메소드를 실행시켜준다.

ex)

```kotlin
fun onClick(v: View) {
    Thread(Runnable {
        // a potentially time consuming task
        val bitmap = processBitMap("image.png")
        imageView.post {
            imageView.setImageBitmap(bitmap)
        }
    }).start()
}
```



### Thread.sleep

- 괄호 안의 수 만큼 멈춤
- 값은 밀리세컨드이므로 1000이면 1초



### try-catch문


- try문 안에는 `예외처리 예상 코드`를 작성
- catch문 안에는 `예외 발생시 처리할 코드`를 작성

```kotlin
            while (true) {
                try {
                    // 스레드에게 수행시킬 동작들 구현
                    Thread.sleep(1000); // 1초간 Thread를 잠재운다
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    ;
                }
```

-  sleep 함수는 반듯이 try~catch 구문으로 감싸야함



### SimpleDateFormat

```kotlin
var timeFormat = SimpleDateFormat("mm:ss") //선언하기

start_tv.text = timeFormat.format(mp.currentPosition) //음원 길이에 맞게 글자에 담기
end_tv.text = timeFormat.format(mp.duration - mp.currentPosition) //전체시간 - 진행시간
```

| Symbol  | 뜻                     | 예시(/로 구분)    |
| ------- | ---------------------- | ----------------- |
| **y**   | 년도                   | 2019, 19          |
| **M**   | 월(1~12)               | 4, 4월, APR       |
| D/**d** | 일(1~366/1~31)         | 139/24, 24일      |
| H/**h** | 시간(0~23/1~12)        | 19/12             |
| K/k     | 시간(1~24/0~11)        | 23/5              |
| **m**   | 분(0~59)               | 23                |
| **s**/S | 초(0~59)/밀리초(0~999) | 42/877            |
| z       | Time Zone              | GMT+9:00          |
| **E**   | 요일                   | 화, 목            |
| w/W     | 연중 주/월중 주        | 44/3              |
| **a**   | 오전/오후              | 오전, AM/오후, PM |