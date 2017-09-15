package backgroundcheck;


public class CriminalRecordChecker implements CriteriaChecker
{

    public ApprovalStatus evaluateCandidate (Candidate candidate)
    {
        if(candidate.getSSN()/100000000 < 6)
            return ApprovalStatus.createRejected("Existing Criminal Record");

        return ApprovalStatus.createApproved();
    }
}
