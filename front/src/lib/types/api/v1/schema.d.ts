/**
 * This file was auto-generated by openapi-typescript.
 * Do not make direct changes to the file.
 */


export interface paths {
  "/api/v1/posts/{id}": {
    /** 글 단건조회 */
    get: operations["getPost"];
    /** 글 편집 */
    put: operations["edit"];
  };
  "/api/v1/posts/temp": {
    /** 임시 글 생성 */
    post: operations["makeTemp"];
  };
  "/api/v1/members/logout": {
    /** 로그아웃 */
    post: operations["logout"];
  };
  "/api/v1/members/login": {
    /** 로그인, accessToken, refreshToken 쿠키 생성됨 */
    post: operations["login"];
  };
  "/api/v1/posts": {
    /** 글 다건조회 */
    get: operations["getPosts"];
  };
  "/api/v1/members/me": {
    /** 내 정보 */
    get: operations["getMe"];
  };
}

export type webhooks = Record<string, never>;

export interface components {
  schemas: {
    Empty: Record<string, never>;
    RsDataEmpty: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["Empty"];
    };
    EditRequestBody: {
      title: string;
    };
    EditResponseBody: {
      item: components["schemas"]["PostDto"];
    };
    PostDto: {
      /** Format: int64 */
      id: number;
      /** Format: date-time */
      createDate: string;
      /** Format: date-time */
      modifyDate: string;
      /** Format: int64 */
      authorId: number;
      authorName: string;
      authorProfileImgUrl: string;
      title: string;
    };
    RsDataEditResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["EditResponseBody"];
    };
    MakeTempResponseBody: {
      item: components["schemas"]["PostDto"];
    };
    RsDataMakeTempResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["MakeTempResponseBody"];
    };
    LoginRequestBody: {
      username: string;
      password: string;
    };
    LoginResponseBody: {
      item: components["schemas"]["MemberDto"];
    };
    MemberDto: {
      /** Format: int64 */
      id: number;
      /** Format: date-time */
      createDate: string;
      /** Format: date-time */
      modifyDate: string;
      name: string;
      profileImgUrl: string;
      authorities: string[];
      social: boolean;
    };
    RsDataLoginResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["LoginResponseBody"];
    };
    GetPostsResponseBody: {
      itemPage: components["schemas"]["PageDtoPostDto"];
    };
    PageDtoPostDto: {
      /** Format: int64 */
      totalElementsCount: number;
      /** Format: int64 */
      pageElementsCount: number;
      /** Format: int64 */
      totalPagesCount: number;
      /** Format: int32 */
      number: number;
      content: components["schemas"]["PostDto"][];
    };
    RsDataGetPostsResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["GetPostsResponseBody"];
    };
    GetPostResponseBody: {
      item: components["schemas"]["PostDto"];
    };
    RsDataGetPostResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["GetPostResponseBody"];
    };
    MeResponseBody: {
      item: components["schemas"]["MemberDto"];
    };
    RsDataMeResponseBody: {
      resultCode: string;
      /** Format: int32 */
      statusCode: number;
      msg: string;
      data: components["schemas"]["MeResponseBody"];
    };
  };
  responses: never;
  parameters: never;
  requestBodies: never;
  headers: never;
  pathItems: never;
}

export type $defs = Record<string, never>;

export type external = Record<string, never>;

export interface operations {

  /** 글 단건조회 */
  getPost: {
    parameters: {
      path: {
        id: number;
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataGetPostResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 글 편집 */
  edit: {
    parameters: {
      path: {
        id: number;
      };
    };
    requestBody: {
      content: {
        "application/json": components["schemas"]["EditRequestBody"];
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataEditResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 임시 글 생성 */
  makeTemp: {
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataMakeTempResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 로그아웃 */
  logout: {
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 로그인, accessToken, refreshToken 쿠키 생성됨 */
  login: {
    requestBody: {
      content: {
        "application/json": components["schemas"]["LoginRequestBody"];
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataLoginResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 글 다건조회 */
  getPosts: {
    parameters: {
      query?: {
        page?: number;
        kw?: string;
        kwType?: "ALL" | "TITLE" | "NAME";
      };
    };
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataGetPostsResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
  /** 내 정보 */
  getMe: {
    responses: {
      /** @description OK */
      200: {
        content: {
          "application/json": components["schemas"]["RsDataMeResponseBody"];
        };
      };
      /** @description Bad Request */
      400: {
        content: {
          "application/json": components["schemas"]["RsDataEmpty"];
        };
      };
    };
  };
}
