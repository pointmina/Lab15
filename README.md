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



15-2. 바인딩 서비스 

서비스를 실행하는 함수를 2개 제공하는 이유?

1. startService() : 백그라운드 작업은 필요하지만 액티비티와 데이터를 주고 받을 일이 없는 등 서로 관련이 없다면 startService()함수로 서비스를 실행란다. , 별로 상호 관계가 없을때
2. bindService() : 서비스와 액티비티가 상호 작용을 해야된다. bindService()는 자신을 실행한 곳에서 객체를 전달한다.


서비스를 bindService() 함수로 실행한 곳에서는 서비스의 onBind() 함수에서 반환한 객체를 
ServiceConnection 인터페이스를 구현한 객체의 onServiceConnected() 함수로 받을 수 있다.

서비스
class MyBinder : Binder() {
  fun funA(arg: Int) {
  }
  fun funB(arg: Int): Int {
    return arg * arg
  }
}
override fun onBind(intent: Intent): IBinder? {
  return MyBinder()
}



액티비티
val connection: Serviceconnection = object : Serviceconnection {
  override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
    servideBinder = service as MyService.MyBinder
  }
  override fun onServiceDisconnected(name: ComponentName?) {
  }
}

service: IBinder? 가 서비스에서 전달한 객체이다.

서비스에서 바인딩한 객체의 함수 호출
servideBinder.funA(10)


bindService는 프로세스간 통신도 가능
- Messenger
- AIDL 

방법 이용가능

15-3 백그라운드 제약
: 예전에는 화면에 출력된 적이 없는 상황에서도 서비스나 브로드 캐스트 리시버로 백그라운드에서 작업을 처리할 수있었지만 안드로이드 8버전(API 레벨 26)부터는 제약을 받는다. -> 제약에 대한 정리가 필요하다.


백그라운드 제약
- Receiver 제약 -> 내부 App을 암시적 실행(x)
- Service 제약 -> App이 background 상황 , Service 구동(x)
- 동일 앱의 암시적 인텐트로 실행되는거가 오류


-> startForegroundService()함수로 실행하고 오류가 나기 전에 startForeground()함수를 호출해 포그라운드 상황으로 만들어야함

백그라운드 상황이더라도 startForegroundService() 함수를 이용하면 서비스를 실행할 수 있지만, 빨리 알림을 이용해 앱을 포그라운드 상황으로 만들어야 한다. 즉, 사용자에게 앱이 실행되고 있다는 것을 알려야한다는 의미이다. 그래야만 앱이 백그라운드 제약에서 벗어날수 있다.

15-4 잡 스케줄러

백그라운드에서 동작을 해서 우리 서비스를 구동시켜야 할 때가 있다. JobService

Jobinfo -> 어떤 상황에서 조건을 만족시키면 system에서 구현(시스템이 허용하는 상황에서)

실행 조건
- 네트워크 타입
- 배터리 충전 상태
- 특정 앱의 콘텐츠 프로바이더 갱신











