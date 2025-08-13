package moobean.saim.server.community.clubMember.controller.port;

import moobean.saim.server.community.clubMember.controller.request.DelegateMasterRequest;

public interface DelegateMasterService {

    void delegateMaster(Long userId, DelegateMasterRequest request);
}
