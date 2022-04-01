package com.example.sinagram2022

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.* //findViewByID를 안쓰고 그냥 id 값으로
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private var isPlaying = false
    private lateinit var mp : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp = MediaPlayer.create(this, R.raw.skz2020) // mp에 음원 담기

// fab 노래 실행을 위해 미리 선언
        fab.setOnClickListener{ //FloatingActionButton 터치 시
            isPlaying = !isPlaying //false에서 true
            if(isPlaying){
                start() // true면 start함수로
            }
            else {
                stop() // false면 stop함수로
            }
        }


// 시크바로 노래 이동
        seekBar.max = mp.duration // mp.duration : 음악 총 시간
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 사용자가 시크바를 움직이면
                if(fromUser){
                    mp.seekTo(progress) // 재생위치를 바꿔줌 (움직인 곳에서의 음악재생)
                }
                var timeFormat = SimpleDateFormat("mm:ss") //"분:초"를 나타낼 수 있도록 포멧팅

                start_tv.text = timeFormat.format(mp.currentPosition)
                end_tv.text = timeFormat.format(mp.duration - mp.currentPosition) //전체시간 - 진행시간
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }


// fab 노래 시작 멈춤
    private fun stop() {
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24) //멈출 때 사진 바꾸기
        mp.pause()
    }

    private fun start(){
        fab.setImageResource(R.drawable.ic_baseline_pause_24) //다시 재생시 원래 사진으로
        mp.start()

        // 실행 중 시스템을 잠깐 멈출 필요가 있을때
        Thread(object: Runnable{ //UI의 변경을 위해 스레드 설정하기
            override fun run(){
                while (mp.isPlaying){
                    try{
                        Thread.sleep(1000) // 1초마다 시크바 움직이게 함
                    } catch (e : Exception){
                        e.printStackTrace() // 에러의 발생근원지를 찾아서 단계별로 에러를 출력
                    }
                    seekBar.setProgress(mp.currentPosition) // 현재 재생중인 위치를 가져와 시크바에 적용
                }
            }
        }).start()
    }
}