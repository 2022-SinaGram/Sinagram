# SinaGram Android

## 뮤직 플레이어 만들기 - XML



### 파일 준비

앨범 사진 : res -> drawable 에 ctrl+c,v

버튼 사진 : res -> drawable -> New -> Vector Asset -> (Clip Art)

- pause
- play_arrow
- skip_next
- skip_previous

음원 : res -> new -> Android Resource Directory -> Resource type : raw 선택 -> 새폴더 만들기

​			raw에 ctrl+c,v (파일 이름 - 대문자, 특수문자 사용 불가)





### 배경색 꾸미기

- 디자인창 -> Widgets -> View x2
- view 동시 선택 후 Chains -> Create Horizontal Chain 으로 묶기
- 3:2 회색 #585d60 , 흰 #fdfcfd
- 레이아웃 세 점 연결
- 코드에서 레이아웃 설정값 변경

```kotlin
android:id="@+id/top_background_view" //이름

// 두개 View 동일
android:layout_width="0dp" //너비 (가로)
android:layout_height="0dp" //높이 (세로)

android:background="#585d60" //회색

app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toTopOf="@+id/bottom_background_view"
app:layout_constraintHorizontal_bias="0.5"
app:layout_constraintVertical_weight="3" //비율
```

```kotlin
android:id="@+id/bottom_background_view"

android:layout_width="0dp"
android:layout_height="0dp"

android:background="#fdfcfd" //흰색

app:layout_constraintTop_toBottomOf="@+id/top_background_view"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintHorizontal_bias="0.5"
app:layout_constraintVertical_weight="2"
```



- 설명

  ```kotlin
  app:layout_constraint방향(이 위젯의 해당 방향에서)_to(대상 위젯의 해당 방향으로)Of = 대상id(@+id/...) or "parent"
  ```

  Top 위 | Bottom 아래 | Start 왼쪽 (시작) | End 오른쪽 (끝)

  ```kotlin
  layout_constraintHorizontal_bias
  - 수평 방향(Left/Right 또는 Start/End) 사이드 제약 시, 양 사이드 간 위치 비율
  - 기본값 0.5 (수평 방향 가운데 위치)
  ```





### 사진 넣기

ImageView

```kotlin
android:id="@+id/imageView"

android:layout_width="350dp"
android:layout_height="350dp"

android:layout_marginTop="150dp" //상단 거리

app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
app:srcCompat="@drawable/skz2020"
```





### 곡제목, 가수명

**TextView** x2

```kotlin
android:id="@+id/title_tv"

android:layout_width="wrap_content"
android:layout_height="wrap_content"

android:layout_marginTop="35dp"

android:text="제목"
android:textSize="30dp"
android:textColor="#FFFFFF"

app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
```

```kotlin
android:id="@+id/name_tv"

android:layout_width="wrap_content"
android:layout_height="wrap_content"

android:layout_marginTop="20dp"

android:text="가수"
android:textSize="15dp"
android:textColor="#f0f0f0"

app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toBottomOf="@+id/title_tv"
```





### 재생 버튼

**FloatingActionButton**

- Vector Asset 사용 전 `Build.gradle`에 추가

```kotlin
android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}
```

- 가로 및 세로 제약조건 하나 이상 연결

```kotlin
android:id="@+id/fab"

android:layout_width="wrap_content"
android:layout_height="wrap_content"

android:layout_marginBottom="50dp" //밑에서부터 50
android:clickable="true" //버튼 눌림 효과

android:backgroundTint="@color/black" //배경색 검정 (원하는 색상)
app:tint="@android:color/white" // (@null) 이미지의 색상이 하얀색임에도 불구하고 fab에서 이미지 색상이 항상 검은색인 이슈를 고치기 위한 코드

app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintBottom_toBottomOf="parent
app:srcCompat="@drawable/ic_baseline_play_arrow_24"
```





### 이전곡 다음곡

**ImageButton**  x2

```kotlin
android:id="@+id/skip_back"

android:layout_width="60dp"
android:layout_height="60dp"

android:scaleType="fitCenter"
// android:scaleType의 옵션값 8개 중 하나
// 이미지의 가로/세로의 길이 중 긴 쪽을 ImageView의 레이아웃에 맞춰서 출력
// 이 때 원본 이미지의 가로/세로의 비율은 유지

android:layout_marginRight="40dp"
android:layout_marginBottom="45dp"

android:backgroundTint="@android:color/transparent" //배경색 투명하게
app:tint="@color/black"

app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toStartOf="@+id/fab"
app:srcCompat="@drawable/ic_baseline_skip_previous_24"
```





### SeekBar

```kotlin
android:id="@+id/seekBar"

android:layout_width="350dp"
android:layout_height="wrap_content"

android:layout_marginBottom="50dp"
app:layout_constraintBottom_toTopOf="@+id/fab"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"

android:thumbTint="@color/black" // 움직이는 동그란 아이콘 색상 (원하는 색으로)
```





### 재생 시간

**TextView** x2

```kotlin
android:id="@+id/current,end_tv"

android:layout_width="wrap_content"
android:layout_height="wrap_content"

android:layout_marginTop="10dp"
android:layout_marginLeft="40dp"

android:text="0:00"

app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toBottomOf="@+id/seekBar"
```
