/*
*  Copyright 2018-2019 Duxiaoman Group Holding Ltd.

*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at

*  http://www.apache.org/licenses/LICENSE-2.0

*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.

*/


package com.dxm.anymock.common.dal.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by jff on 2019/8/26.
 */

public class TcpInterfaceKeyBO {

    public interface Insert {
    }

    public interface Update {
    }

    @NotBlank(groups = {TcpInterfaceKeyBO.Insert.class, TcpInterfaceKeyBO.Update.class})
    @Pattern(regexp = "^[0-9]*$", groups = {TcpInterfaceKeyBO.Insert.class, TcpInterfaceKeyBO.Update.class})
    private String requestUri;

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }


}
