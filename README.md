# Android Team 1RM - SparTube

# 9.27(수) 1차 main PR
- 필수/추가/재생목록 기능 구현
- 디자인 회의
# 10.05(월) 2차 최종 main PR
- 필수/추가/재생목록 추가 기능 구현 및 수정
- 디자인 회의 및 구현 진행 및 수정

## 목차 -> 추후 변경 가능
1. 프로젝트 명
2. 프로젝트 설명
3. 개발 기간
4. 주요 기능
5. 팀원 및 역할 분담
6. 트러블 슈팅
7. 참고 자료 / 외부 리소스 정보
8. 팀 노션 주소
9. 라이센스

## 프로젝트 명
Asian Game Clip

## 프로젝트 설명
유튜브 API를 이용하여 제19회 항저우 아시안게임을 주제로 가장 인기있는 동영상/ 가장 인기있는 쇼츠/ 관련있는 채널 목록을 보여 주고
좋아요/즐겨찾기/링크공유 기능이 있는 영상 썸네일을 제공하는 어플이다.

## ⏰ 개발 기간
23.09.25 ~ 23.10.06
  - 9.27 1차 PR
  - 10.05 2차 PR

23.10.05 18:00 제출

23.10.06 14:00 발표

## ⚙️ 주요 기능
- 메인(Home) 페이지
  - 가장 인기있는 동영상/ 가장 인기있는 쇼츠/ 관련있는 채널 목록 보여주기
  - 영상 썸네일 클릭 시 디테일 페이지로 화면 전환
- 검색(Search) 페이지
  - 랜덤 아시안 게임 종목 자동완성 기능
  - 검색 기능
  - 영상 썸네일 클릭 시 디테일 페이지로 화면 전환
- 재생목록(Playlist) 페이지
  - 재생목록 추가
  - 영상 썸네일 클릭 시 디테일 페이지로 화면 전환
- 내 정보(My) 페이지
  - 내 정보 확인
  - 좋아요 취소 기능
  - 영상 썸네일 클릭 시 디테일 페이지로 화면 전환
- 상세 정보(Detail) 페이지
  - 좋아요/즐겨찾기/링크공유 기능
  - 링크 공유시 url이 자동복사 되어 메시지로 전달 가능

## 🎉 팀원 및 역할 분담
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/LeeDonghee0917"><img src="https://avatars.githubusercontent.com/u/86705733?v=4" width="100px;"><br /><sub><b>AOS 팀장 : 이동희</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/VonLyus"><img src="https://avatars.githubusercontent.com/u/73988800?v=4" width="100px;"><br /><sub><b>AOS 팀원 : 나유성</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/JunBeoPark"><img src="https://avatars.githubusercontent.com/u/94429684?v=4" width="100px;"><br /><sub><b>AOS 팀원 : 박준범</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/ckh124"><img src="https://avatars.githubusercontent.com/u/113021323?v=4" width="100px;"><br /><sub><b>AOS 팀원 : 조광희</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/lsshhh"><img src="https://avatars.githubusercontent.com/u/134198247?v=4" width="100px;"><br /><sub><b>AOS 팀원 : 이승훈</b></sub></a><br /></a></td>
     <tr/>
  </tbody>
</table>

1. 이동희
   - 메인 액티비티 탭 레이아웃 및 뷰페이저2 구현
   - 유튜브 API 레트로핏 구현
   - 메인 페이지 UI 구현
   - 메인 페이지 인기 동영상/ 인기 쇼츠/ 관련 채널 기능 구현
   - PR 및 issue 관리
   - 발표
2. 나유성
   - 유튜브 API 레트로핏 구현
   - 디테일 페이지 UI 구현
   - 디테일페이지 좋아요/즐겨찾기/링크공유 기능 구현
   - 메인 페이지 인피니티 스크롤 기능 구현
   - 홈(영상/쇼츠/채널), 검색(영상) 별 url 링크 페이지 유튜브로 이동 구현
   - Fragment 연결 관리
   - PR 및 issue 관리
3. 박준범
   - 유튜브 API 레트로핏 구현
   - 재생목록 페이지 재생목록 추가 기능 구현
   - 재생목록 페이지 UI 구현
   - PR 및 issue 관리
4. 조광희
   - 유튜브 API 레트로핏 구현
   - 마이페이지 UI 구현
   - 마이페이지 내 정보 확인 및 좋아요 취소 기능 구현
   - 메인 페이지 인피니티 스크롤 기능 구현
   - 홈(영상/쇼츠/채널), 검색(영상) 별 url 링크 페이지 유튜브로 이동 구현
   - Fragment 연결 관리
   - PR 및 issue 관리
5. 이승훈
   - 유튜브 API 레트로핏 구현
   - 검색 페이지 UI 구현
   - 검색 페이지 검색 기능 구현
   - 랜덤 아시안 게임 종목 자동완성 기능 구현
   - 검색 페이지 인피니티 스크롤 기능 구현

##트러블슈팅
1. 유튜브 API에서 쇼츠의 값만 받기가 안되는 문제
    - 문제 사항
      - 유튜브 검색 API의 데이터를 받아서 쇼츠만 걸러내는 것이 안되는 문제
    - 시도
      - Video Categories id = ‘42’ -> 결과 상 쇼츠의 카데고리 id가 맞지만 데이터 결과가 이상하게 나왔다.
      - videoDuration = 'short' -> 4분 미만의 동영상이라 따로 영상 길이를 60초 이하로 줄여야 하고, 쇼츠 뿐만 아니라 영상도 같이 나왔다.
      - https://www.youtube.com/shorts/비디오id -> API의 값이 url이 아니라 비디오id만 받을 수 있어서 url로 제한을 해도 영상도 같이 나왔다.
    - 해결 방법
      - Q 검색어 지정에 “쇼츠 Shorts” 추가
    - 느낀 점
      - 가장 간단하고 단순한 방법이 의외의 해결 방법일 수 있다는 것을 알았다.
2. 인피니티 스크롤 실행 시 API 함수가 2번 참조되는 문제
    - 문제 사항
      - 인피니티 스크롤을 구현 한 후, 끝 라인에서 스크롤을 계속 잡아 끌었을 때, API로 받아온 데이터가 로딩이 안되었지만 스크롤이 끝라인이 닿음이 감지되면서 API 함수가 한번 더 참조되는 상황이 발생했다.
    - 시도
      - if문을 통해 PageToken을 참조해 API코드를 실행, 이를 활용하기 위해서는 API코드가 실행되어야 되는 상태인데, 실행되기 전 참조가 발생하여 실패했다.
    - 해결 방법
      - LodingViewHolder를 생성하여 Loding이 되기 전까지 API함수가 참조되지 않게 구현, API함수가 끝난 후 LodingViewHolder를 삭제하여 이중호출이 안되게 설정했다.
    - 느낀 점
      - 사용자의 앱 사용방법에 따라 다양한 에러가 발생할 수도 있겠다는 생각이 들었다.
3. 인피니티 스크롤 기능에 대한 설정 방향성 문제
    - 문제 사항
      - horizontal인 recyclerView를 한정된 데이터 량이라고 설정 후에 왼쪽 슬라이딩, 오른쪽 슬라이딩을 하였을 때 무한으로 스크롤이 되게 설정됐다
    - 시도
      - 스크롤 마지막에 갔을때 끊기는 현상을 없애려고 5번째 전에 생성하고 판단해서 구현하려고 했지만 나아지지 않았다.
    - 해결 방법
      - 튜터님께 방향성을 설명을 받은 후, 설정했던 방법이나 구현하려는 기능은 recyclerView, ViewPager의 구현된 기능의 방향성과 달라서 다른 방향성 설정했다.
    - 느낀 점
      - 기능에 대해 상상은 가능하지만 좀 더 현재 사용하는 기능에 대해 깊게 공부해보고 적용을 할 수 있는지 판단을 해야겠다 생각했다.

## ♻️ 참고 자료 / 외부 리소스 정보
- 재생목록 리사이클러뷰 클릭이벤트: https://juahnpop.tistory.com/235
- 인피니티 스크롤: https://todaycode.tistory.com/12

## 📖 팀 노션
https://teamsparta.notion.site/16-Team-3-500-SA-4ee02e52d5fe4011a0643711d9ea6642

## 기술스택
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"><img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=Android&logoColor=white">

##라이센스
