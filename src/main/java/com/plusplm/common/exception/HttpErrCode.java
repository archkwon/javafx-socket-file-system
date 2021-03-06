package com.plusplm.common.exception;

public class HttpErrCode {
	
	final public static int _IDX_CODE = 0;
	
	final public static int _IDX_MEAN = 1;
	
	
	/**
	 * 클라이언트의 요청이 불안전하며, 클라이언트 요청을 성공시키려면 다른 정보가 필요하다는 것을 말합니다.
	 * 상태 코드의 4xx 클래스는 클라이언트가 에러를 발생한 것처럼 보일 경우에 사용됩니다. HEAD 요구에 응답하는 경우를 제외하고는 서버는 임시적이건 영구적이건 에러 상황에 대한 설명을 포함한 엔터티를 포함해야 합니다. 이러한 상태 코드는 모든 요구 method에 적용할 수 있습니다. 사용자 에이전트는 사용자에게 포함된 엔터티를 표시해야 합니다. 
	 * 
	 * [주] 클라이언트가 데이터를 발송한다면 TCP를 사용하는 서버 구현 방식은 서버가 입력 접속을 종료하기 전에 응답을 포함하고 있는 패킷 접수를 확인할 수 있도록 주의해야 합니다. 클라이언트가 접속이 종료된 후에도 계속해서 데이터를 전송한다면 서버의 TCP 스택은 리셋 패킷을 클라이언트에게 발송할 것입니다.
	 * 이 리셋 패킷은 HTTP 애플리케이션이 읽거나 해석하기 전에 클라이언트가 확인한 입력 버퍼를 지웁니다.
	 */
	final public static Object[] _ERR_CODE_400 = {400, "Bad Request"};
	/**
	 * 클라이언트의 요청에 문법적인 오류가 있다는 것을 서버가 알아냈다는 것을 의미합니다.
	 * 잘못된 형식 때문에 서버가 요구를 이해할 수 없습니다. 클라이언트는 변경 없이 요구를 반복해서는 안 됩니다.
	 */
	final public static Object[] _ERR_CODE_401 = {401, "Unauthorized"};    
	/**
	 * 이 코드는 아직 HTTP로 구현되지 않았습니다. 하지만 언젠가는 서버의 문서를 받아보기 위해 지불이 필요하다는 것을 나타냅니다.
	 */
	final public static Object[] _ERR_CODE_402 = {402, "Payment Request"};   
	/**
	 * 클라이언트의 인증정보에 상관없이 페이지에 대한 접근을 거부한다는 것을 나타냅니다.
	 * 서버가 요구를 이해했으나 완료하는 것을 거절하고 있다는 의미로 인증은 적용되지 않으며 요구를 반복될 수 없습니다. 요구 method가 HEAD가 아니고 서버가 왜 요구가 완료되었는지 알리고 싶다면 엔터티 안에 거절한 이유를 기록해야 합니다. 이 상태 코드는 서버가 요구가 거부 사유를 밝히기 원하지 않을 때나 다른 응답을 적용할 수 없을 때 일반적으로 사용됩니다.
	 */
	final public static Object[] _ERR_CODE_403 = {403, "Forbidden"}; 
	/**
	 * 클라이언트가 요청한 자원에 서버에 없다는 것을 나타냅니다.
	 * 서버가 Request-URI와 일치하는 것을 아무것도 발견하지 못했다는 의미로 이러한 상태가 잠정적인지 영구적인지 관한 아무런 표시도 주어지지 않은 경우입니다. 
	 */
	final public static Object[] _ERR_CODE_404 = {404, "Not Found"};           
	/**
	 * 서버가 이 정보를 클라이언트에게 알리고 싶지 않을 경우 상태 코드 403(Forbidden)을 대신 사용할 수 있습니다. 내부적으로 환경을 설정할 수 있는 메커니즘을 통하여 이전의 자원을 영구적으로 사용할 수 없으며 전송 주소가 없다는 것을 알 수 있으면 410(Gone) 상태 코드를 사용합니다.
	 */
	final public static Object[] _ERR_CODE_405 = {405, "Method Not Allowd"};   
	/**
	 *  Allow 헤더와 함께 클라이언트가 사용한 메소드가 이 URI에 대해 지원되지 않는다는 의미입니다.
	 *  Request-Line에 명시된 method를 Request-URI로 확인할 수 있는 자원에서 사용할 수 없습니다.
	 *  응답은 요구된 자원에 사용할 수 있는 method의 목록을 포함한 Allow 헤더를 포함해야 합니다.
	 */
	final public static Object[] _ERR_CODE_406 = {406, "Not Acceptable"};
	/**
	 * 클라이언트가 지정한 URI는 존재하지만 클라이언트가 원하는 형식이 아닙니다.
	 * 코드와 함께 서버는 Content-Language, Content-Encoding, Content-Type 헤더를 제공합니다. HEAD 요구가 아닌 이상 응답은 사용자 또는 사용자 에이전트가 가장 적합한 것을 선택할 수 있는 자원 특징 및 위의 목록을 포함한 엔터티를 포함합니다. 엔터티 포맷은 Content-Type 헤더 필드가 설정한 media type에 의해 명시됩니다. 사용자 에이전트의 포맷 및 성능에 따라 가장 적합한 선택을 결정하는 것은 자동으로 수행될 수 있습니다. 그러나 이 규격은 그러한 자동 선택의 표준에 대하여 아무런 규정도 하지 않습니다. 
	 * 
	 * 
	 *  [주] HTTP/1.1 서버는 요구 메시지와 함께 발송된 Accept 헤더에 의해서 접수할 수 없는 응답을 리턴할 수 있게 합니다. 어떤 경우엔 이것이 406 응답을 발송하는 것보다 좋을 수도 있습니다. 사용자 에이전트는 도착하는 응답의 헤더를 검사하여 그것의 접수 여부를 결정하도록 추천합니다. 응답을 접수할 수 없을 때 사용자 에이전트는 잠정적으로 더 이상의 데이터를 수신하지 말아야 하며 추가 행동을 취할 것인지 사용자에게 질의합니다.
	 */
	final public static Object[] _ERR_CODE_407 = {407, "Proxy Authentication Required"};
	/**
	 *  이 코드는 401(Unauthorized)과 유사하지만 클라이언트는 먼저 프락시에서 자기 자신을 인증해야 한다는 것을 표시 하는 것으로 proxy 서버로 로그온 한 후에 다시 시도해 봐야 합니다.
	 *  프락시는 요구된 자원의 프락시에 적용할 수 있는 설명 요구를 포함하는 Proxy-Authenticate 헤더 필드를 리턴해야 합니다. 클라이언트는 적절한 Proxy-Authorization 헤더 필드와 함께 요구를 반복해야 합니다.
	 */
	final public static Object[] _ERR_CODE_408 = {408, "Request Timeout"};
	/**
	 * 다른 요청이나 서버의 구성과 충돌이 있음을 나타냅니다.
	 * 충돌에 대한 정보는 응답되는 데이터의 일부로 반환됩니다. 이 코드는 사용자가 충돌을 해결하고 요구를 재전송할 수 있을 것으로 기대할 수 있는 상황에서만 사용할 수 있습니다. 응답 본문은 사용자가 충돌의 원인을 인지할 수 있도록 충분한 정보를 포함해야 합니다. 이상적으로는 응답 엔터티가 사용자 또는 사용자 에이전트가 문제를 해결할 수 있을 정도의 충분한 정보를 포함할 수 있을 것입니다. 그러나 가능하지 않을 수도 있으며 필수 사항은 아닙니다. 
	 * 
	 * 충돌은 PUT 요구에 대한 응답으로 발생할 가능성이 높습니다. 버전 관리를 사용하고 있고 PUT 요구를 하는 엔터티가 이전 요구(제 3 자)가 작성한 요구와 충돌되는 자원에 대한 변경 사항을 포함하고 있다면 서버는 409 응답을 사용하여 요구를 완료할 수 없음을 표시해야 합니다. 이 경우 응답 엔터티는 응답 Content-Type이 규정한 형식으로 두 버전 사이의 차이점 목록을 포함해야 합니다.
	 */
	final public static Object[] _ERR_CODE_409 = {409, "Conflict"};
	/**
	 *  요청된 문서가 사라지고, 새로운 주소는 알 수 없다는 것을 나타냅니다.
	 *  요구된 자원이 서버에 더 이상 존재하지 않으며 전송 주소를 알 수 없는 경우입니다. 이 조건은 영구적인 것으로 간주해야 합니다. 링크를 편집할 기능이 있는 클라이언트는 사용자 인증 후의 Request-URI에 대한 참고는 삭제해야 합니다. 서버가 그 조건이 영구적인지 여부를 알 수 없거나 결정할 시설이 없으면 상태 코드 401(Unauthorized)을 대신 사용해야 합니다. 다르게 표시되지 않는 한 이 응답은 캐시할 수 있습니다. 
	 * 
	 *  410 응답은 주로 수신측에게 자원을 의도적으로 사용할 수 없게 하였고 서버의 소유주가 해당 자원에 대한 원격 링크를 제거하고자 한다는 것을 알림으로써 웹 유지 작업을 지원하기 위해 사용됩니다. 이러한 일은 제한된 시간, 선전용 서비스 및 서버의 사이트에서 더 이상 일하지 않는 개인에게 소속된 자원에서 공통적으로 발생할 수 있습니다. 영구적으로 사용할 수 없는 모든 자원을 "사라진" 것으로 표시하거나 특정 시간 동안 표시를 유지할 필요는 없습니다.
	 */
	final public static Object[] _ERR_CODE_410 = {410, "Gone"};
	/**
	 * 
	 *  서버가 규정된 Content-Length 없는 요구 접수를 거부하였다는 의미로 요구 메시지 내의 Message-Body의 길이를 포함하는 유효한 Content-Length 헤더 필드를 추가한다면 클라이언트는 요구를 반복할 수 있습니다.
	 */
	final public static Object[] _ERR_CODE_411 = {411, "Length Required"};
	/**
	 *  하나 또는 그 이상의 Request-Header에 명시된 조건에 의해 요청을 평가하여 false값을 가지는 경우입니다. 이 응답 코드는 클라이언트가 현재 자원의 메타 정보에 사전 조건을 부여할 수 있게 하여 의도하지 않는 자원에 요구 method를 적용하는 것을 방지합니다.
	 */
	final public static Object[] _ERR_CODE_412 = {412, "Precondition"};
	/**
	 * 서버는 실제 본문이 너무 커서 요청을 처리할 수 없다는 것을 의미합니다.
	 * 요구 엔터티가 서버가 처리할 수 있거나 처리하려는 것보다 크기 때문에 서버가 요구 처리를 거부하는 경우로 서버는 클라이언트가 계속적으로 요구하는 것을 방지하기 위하여 연결을 종료합니다. 
	 * 
	 * 조건이 잠정적이면 서버는 Retry-After 헤더 필드를 포함하여 조건이 잠정적이며 얼마 후에 클라이언트가 재시도할 것인지를 표시합니다.
	 */
	final public static Object[] _ERR_CODE_413 = {413, "Request Entity Too Large"};
	/**
	 *  서버는 요청된 URI가 너무 커서 요청을 처리할 수 없다는 것을 의미합니다.
	 *  Request-URI가 서버가 해석할 수 있는 것보다 크기 때문에 서버가 요구 처리를 거부하는 경우로 이처럼 드문 조건은 클라이언트가 부적절하게 질의 정보가 긴 POST 요구를 GET 요구로 변환했을때, 클라이언트가 방향 재설정의 URL "블랙 홀"로 빠졌을 때(방향이 재설정된 URL 접두사가 자신의 접미사를 지칭할 때), Request-URI를 읽거나 조작하는 고정-길이 버퍼를 사용하는 몇몇 서버에 존재하는 보안의 허점을 이용하려는 클라이언트로부터 서버가 공격을 받을 때만 발생하는 것 같습니다.
	 */
	final public static Object[] _ERR_CODE_414 = {414, "Request-URI too Long"};
	/**
	 * 서버는 실제 본문이 지원되지 않는 형식이라 처리할 수 없다는 의미입니다.
	 * 요구의 엔터티가 요구 받은 method의 자원이 지원하지 않는 포맷으로 구성되어 있기 때문에 요구처리를 거부하였다는 의미입니다.
	 */
	final public static Object[] _ERR_CODE_415 = {415, "Unsupported Media Type"};
	/**
	 *  서버는 어떤 유효한 값도 포함하지 않은 Range 헤더를 찾아냈습니다.
	 *  추가로 If-Range헤더는 없어졌습니다.
	 */
	final public static Object[] _ERR_CODE_416 = {416, "Requested range not satisfiable"};
	/**
	 *   Exept헤더에서 명시된 조건은 만족될 수 없습니다A
	 */
	final public static Object[] _ERR_CODE_417 = {417, "Expectation Fail"};
	
	
	
}
