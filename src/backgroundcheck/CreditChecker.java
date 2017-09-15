package backgroundcheck;

public class CreditChecker implements CriteriaChecker
{
    public ApprovalStatus evaluateCandidate(Candidate candidate)
    {
        if(candidate.getSSN()/100000000 < 6)
            return ApprovalStatus.createRejected("Bad Credit");

        return ApprovalStatus.createApproved();
    }
}
