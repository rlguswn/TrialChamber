# TrialChamber
- 주제(서비스명): 문제 풀이 플랫폼
    - 일부 출제자가 문제를 업로드하고 학생들이 풀 수 있는 문제 풀이 플랫폼

# 개발 기간
- 24.08.06 ~ 진행중

# 개발 전 요구사항(기능)
- 회원 기능
    - 사용자 등록
        - 사용자가 회원 가입 신청 후 관리자가 승인하는 방식
        - 개인정보 입력 최대한 배제
    - 사용자 로그인
- 문제 기능
    - 문제 게시
        - 반복 사용되는 문제 형식은 편하게 만들 수 있도록 하는 기능
        - 문제에 응시 가능한 사용자 지정 기능
    - 문제 관리
        - 문제 게시 후 수정 혹은 삭제 기능
    - 문제 풀이 제출
        - 동일한 사용자가 하나의 문제에 대해 중복 제출 방지
    - 제출된 답안 검토
        - 출제자가 정답 혹은 오답 확인
        - 자동으로 점수 집계
- 관리자 기능
    - 회원 가입 신청한 사용자 승인 혹은 거절 기능

# DataBase Structure
![trialchamber erd](https://github.com/user-attachments/assets/b6c93d41-37c3-423f-b5ea-7ef9bfa92438)

# 배포 URL
(프로젝트 유지 보수 및 개선을 위한 배포 중단)

# Index
[1. 기술스택 & 개발환경](#1-기술-스택--개발-환경)  
[2. 프로젝트 요약](#2-프로젝트-요약)  
[3. 주요 기능 소개](#3-주요-기능-소개)  
[4. 라이브 데모](#4-기능별-라이브-데모)  

# 1. 기술 스택 & 개발 환경
<table>
  <thead>
    <tr>
      <th>프론트엔드</th>
      <th>백엔드</th>
      <th>배포</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
      <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
      <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
      <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">
      <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
      </td>
      <td>
      <img src="https://img.shields.io/badge/Java-000000?style=for-the-badge&logo=openjdk&logoColor=white">
      <img src="https://img.shields.io/badge/JPA-000000?style=for-the-badge&logo=openjdk&logoColor=white">
      <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
      <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
      <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
      <img src="https://img.shields.io/badge/JPQL-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
      </td>
      <td>
      <img src="https://img.shields.io/badge/AWS EC2-232F3E?style=for-the-badge&logo=amazonwebservices&logoColor=white">
      <img src="https://img.shields.io/badge/Docker Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white">
      <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
      </td>
    </tr>
  </tbody>
</table>

# 2. 프로젝트 요약
- 서비스 전체 개요
  - 문제 풀이 플랫폼 TrialChamber는 선생님-학생 간 자유롭게 영어 문제 제공 및 풀이가 이루어지는 공간입니다.
  - 선생님은 유형별 영어 문제를 출제하고 학생별 문제 풀이 현황을 확인할 수 있습니다.
  - 학생은 본인의 문제 풀이 현황을 직접 확인할 수 있으며 일부 자동 채점이 활성화 된 문제는 풀이 제출 직후 바로 점수를 확인할 수 있습니다.
  - 관리자는 신규 가입 인원에 권한을 부여할 수 있으며 전체 유저 및 테스트를 관리할 수 있습니다.
- 서비스 개발 관점
  - Thymeleaf를 사용해 데이터 바인딩, 템플릿 재사용, 보안 관리를 도모하였습니다.
  - 서비스를 실 배포하여(AWS EC2) 제작한 프로젝트를 테스트해볼 수 있도록 하였습니다.
  
# 3. 주요 기능 소개
업데이트 예정

# 4. 기능별 라이브 데모
업데이트 예정