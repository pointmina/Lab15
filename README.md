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









