package backgroundcheck;

import java.util.Optional;

public class ApprovalStatus
{
    private boolean approved;
    private String reason;

    private ApprovalStatus() {}

    public static ApprovalStatus createApproved()
    {
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.approved = true;

        return approvalStatus;
    }
    
    public static ApprovalStatus createRejected(String reason)
    {
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.approved = false;
        approvalStatus.reason = reason;

        return approvalStatus;
    }

    public boolean isDisapproved() {return !approved;}

    public Optional<String> getReason()
    {
        return Optional.ofNullable(reason);
    }
}