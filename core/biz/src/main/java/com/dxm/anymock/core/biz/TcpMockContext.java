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


package com.dxm.anymock.core.biz;

import com.dxm.anymock.common.dal.model.InterfaceBranchBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;

/**
 * Created by jff on 2019/8/30.
 */

public class TcpMockContext {
    public TcpInterfaceBO getTcpInterfaceBO() {
        return tcpInterfaceBO;
    }

    public void setTcpInterfaceBO(TcpInterfaceBO tcpInterfaceBO) {
        this.tcpInterfaceBO = tcpInterfaceBO;
    }

    public InterfaceBranchBO getInterfaceBranchBO() {
        return interfaceBranchBO;
    }

    public void setInterfaceBranchBO(InterfaceBranchBO interfaceBranchBO) {
        this.interfaceBranchBO = interfaceBranchBO;
    }

    private TcpInterfaceBO tcpInterfaceBO;
    private InterfaceBranchBO interfaceBranchBO;
}
