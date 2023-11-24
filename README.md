# Lab15

15-1. 서비스 이해하기

서비스 : 오래 걸리는 작업을 백그라운드에서 처리할 수 있게 해주는 컴포넌트이며 생명주기를 시스템에서 관리한다.
=> 화면에는 아무것도 안보인다.

등록 :
<servie name = "A"...>

실행하고자 하는 곳에서 인텐트를 준비한다.
val intent = Intent()

서비스 실행 두가지
- startService(intent) = > 서비스를 구동시키는 인텐트를 시스템에 발생시키는 함수
- bindService
뭘 사용하느냐에 따라 서비스의 라이프 사이클이 바뀐다.


이미 서비스가 동작되는데 또 스타트 서비스를 하면 어케되냐?
- 액티비티는 인텐트 단위로 객체가 생성된다.
- 서비스는 화면과 상관 없기 때문에 하나의 객체로 움직인다.(onStartCommend만 반복해서 실행된다.)


ex)

class MyService : Service() {
  override fun onBind(intent: Intent): IBinder? {
    return null
  }
}

서비스도 컴포넌트이므로 매니페스트에 등록해야한다.

서비스에는 종료 인텐트도 있다.
stopService(intent)

![image](https://github.com/pointmina/Lab15/assets/68779817/610d8510-2d1c-4a8d-8ac4-8540017057b1)




