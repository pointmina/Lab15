# Lab15

15-1. 서비스 이해하기

서비스 : 오래 걸리는 작업을 백그라운드에서 처리할 수 있게 해주는 컴포넌트이며 생명주기를 시스템에서 관리한다.

서비스 컴포넌트를 작성할 때는 onBind()함수가 필수다. 
ex)

class MyService : Service() {
  override fun onBind(intent: Intent): IBinder? {
    return null
  }
}

서비스도 컴포넌트이므로 매니페스트에 등록해야한다.
