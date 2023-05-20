# 📢 소문나: 소음의 문제는 나!
아파트에 살면서 층간 소음이 내 탓일 수도 있다는 생각해 봤어?!

## SOPKATHON-13-SERVER 
 
### 🧑‍💻 Developers
| 전선희 | 이의제 |
| :---: | :---: | 
|[funnysunny08](https://github.com/funnysunny08)|[euije](https://github.com/euije)| 


 ### 🐾 Roles
| 기능명 | 엔드포인트 | 담당자 |
| :-----: | :---: | :---: |
| 로그인 | [GET] /user | `전선희` | 
| 오늘 찌르기 조회 | [GET] /user/{fromHomeNumber=Long}/complain | `이의제` | 
| 찌르기 | [POST] /complain | `전선희` | 
| 소음 고지서 | [GET] /user/{homeNumber=Long}/weekly | `전선희` | 
| 소음 고지서 리스트 | [GET] /user/{homeNumber=Long}/weekly | `이의제` | 

## 🙏 Code Convention

> 💡 **동료들과 말투를 통일하기 위해 컨벤션을 지정합니다.**
> 오합지졸의 코드가 아닌, **한 사람이 짠 것같은 코드**를 작성하는 것이 추후 유지보수나 협업에서 도움이 됩니다. 내가 코드를 생각하면서 짤 수 있도록 해주는 룰이라고 생각해도 좋습니다!

<details>
<summary>명명규칙(Naming Conventions)</summary>
<div markdown="1">

1. 이름으로부터 의도가 읽혀질 수 있게 쓴다.

-   ex)

    ```jsx
    // bad
    function q() {
        // ...stuff...
    }

    // good
    function query() {
        // ..stuff..
    }
    ```

2. 오브젝트, 함수, 그리고 인스턴스에는 `camelCase`를 사용한다.

-   ex)
    ```jsx
    // bad
    const OBJEcttsssss = {};
    const this_is_my_object = {};
    function c() {}

    // good
    const thisIsMyObject = {};
    function thisIsMyFunction() {}
    ```

3. 클래스나 constructor에는 `PascalCase`를 사용한다.

-   ex)
    ```jsx
    // bad
    function user(options) {
        this.name = options.name;
    }

    const bad = new user({
        name: 'nope',
    });

    // good
    class User {
        constructor(options) {
            this.name = options.name;
        }
    }

    const good = new User({
        name: 'yup',
    });
    ```

4. 함수 이름은 동사 + 명사 형태로 작성한다.
   ex) `postUserInformation( )`
5. 약어 사용은 최대한 지양한다.
6. 이름에 네 단어 이상이 들어가면 팀원과 상의를 거친 후 사용한다
 </div>
 </details>

<details>
<summary>블록(Blocks)</summary>
<div markdown="1">

1. 복수행의 블록에는 중괄호({})를 사용한다.

-   ex)
    ```jsx
    // bad
    if (test)
      return false;

    // good
    if (test) return false;

    // good
    if (test) {
      return false;
    }

    // bad
    function() { return false; }

    // good
    function() {
      return false;
    }

    ```

2. 복수행 블록의 `if` 와 `else` 를 이용하는 경우 `else` 는 `if` 블록 끝의 중괄호( } )와 같은 행에 위치시킨다.

-   ex)
    ```java
    // bad
    if (test) {
    thing1();
    thing2();
    }
    else {
    thing3();
    }

    // good
    if (test) {
      thing1();
      thing2();
    } else {
      thing3();
    }

    ```
</div>
</details>

<details>
<summary>코멘트(Comments)</summary>
<div markdown="1">

1. 복수형의 코멘트는 `/** ... */` 를 사용한다.

-   ex)
    ```jsx
    // good
    /**
     * @param {String} tag
     * @return {Element} element
     */
    
    function make(tag) {
        // ...stuff...

        return element;
    }
    ```

2. 단일 행의 코멘트에는 `//` 을 사용하고 코멘트를 추가하고 싶은 코드의 상부에 배치한다. 그리고 코멘트의 앞에 빈 행을 넣는다.

-   ex)
    ```jsx
    // bad
    const active = true; // is current tab

    // good
    // is current tab
    const active = true;

    // good
    function getType() {
        console.log('fetching type...');

        // set the default type to 'no type'
        const type = this._type || 'no type';

        return type;
    }

    ```
</div>
</details>


### 🌿 Branch Strategy

<details>
<summary>Git Workflow</summary>
<div markdown="1">       

```
main → develop → feature/#issue_num
issue_num : issue 번호에 맞게 생성

1. issue 생성
2. local - feature/#issue_num 에서 각자 기능 작업 (issue_num : issue 번호에 맞게 생성)
3. remote - feature/#issue_num 에 Push
4. remote - develop 으로 PR
5. 코드 리뷰 후 Confirm 받고 remote - develop Merge
6. remote - develop 에 Merge 될 때 마다 모든 팀원 local - develop pull 받아 최신 상태 유지
 ```

</div>
</details>


| Branch Name | 설명 |
| :---: | :-----: |
| main | 초기 세팅 존재 |
| develop | 구현 완료 브랜치 |
| feature/#issue_num | 이슈 별 기능 구현 브랜치 |


### 📌 Commit Convention

##### [TAG] 메시지 => [feature/#issue_num] TAG: commit message

| 태그 이름  |                             설명                             |
| :--------: | :----------------------------------------------------------: |
|  [CHORE]   |                  코드 수정, 내부 파일 수정                   |
|   [FEAT]   |                       새로운 기능 구현                       |
|   [ADD]    | FEAT 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성 |
|  [HOTFIX]  |             issue나 QA에서 급한 버그 수정에 사용             |
|   [FIX]    |                       버그, 오류 해결                        |
|   [DEL]    |                     쓸모 없는 코드 삭제                      |
|   [DOCS]   |                 README나 WIKI 등의 문서 개정                 |
| [CORRECT]  |       주로 문법의 오류나 타입의 변경, 이름 변경에 사용       |
|   [MOVE]   |               프로젝트 내 파일이나 코드의 이동               |
|  [RENAME]  |                파일 이름 변경이 있을 때 사용                 |
| [IMPROVE]  |                     향상이 있을 때 사용                      |
| [REFACTOR] |                   전면 수정이 있을 때 사용                   |


### 📁 Project Foldering
```
📁 server _
|_ 📁 common _
|_ 📁 controller _
|_ 📁 domain _
|_ 📁 exception _
|_ 📁 infrastructure _
|_ 📁 service _
|_ ServerApplication
```

### 🥫 ERD
<img width="393" alt="image" src="https://github.com/funnysunny08/Algorithm-java/assets/88873302/49a48444-05cb-4995-b06d-bdff7ac38cfb">

<img width="326" alt="image" src="https://github.com/funnysunny08/Algorithm-java/assets/88873302/4ff0438a-9339-47ad-9387-f8400a78e7df">

💕 솝커톤 우승 참 13~ 💕
