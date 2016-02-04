package edu.biniek.evoting.voting;

import edu.biniek.evoting.VotingConfirmation;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class VotingTest {
    BallotFactory ballotFactory = new BallotFactory("Anthony", "Bill");

    @Test
    public void should_count_zero_votes() throws Exception {
        // given
        Voting voting = new Voting(ballotFactory.getBallots(1));
        // then
        assertThat(voting.countVotesFor("Anthony")).isEqualTo(0);
        assertThat(voting.countVotesFor("Bill")).isEqualTo(0);
    }

    @Test
    public void should_accept_vote() throws Exception {
        // given
        Voting voting = new Voting(ballotFactory.getBallots(1));
        // when
        voting.vote((Ballot b) -> {b.check("Bill");});
        // then
        assertThat(voting.countVotesFor("Anthony")).isEqualTo(0);
        assertThat(voting.countVotesFor("Bill")).isEqualTo(1);
    }

    @Test
    public void should_accept_multiple_votes() throws Exception {
        // given
        Voting voting = new Voting(ballotFactory.getBallots(3));
        // when
        voting.vote((Ballot b) -> {b.check("Bill");});
        voting.vote((Ballot b) -> {b.check("Bill");});
        voting.vote((Ballot b) -> {b.check("Bill");});
        // then
        assertThat(voting.countVotesFor("Anthony")).isEqualTo(0);
        assertThat(voting.countVotesFor("Bill")).isEqualTo(3);
    }

    @Test
    public void should_return_correct_confirmation_code() throws Exception {
        // given
        Voting voting = new Voting(ballotFactory.getBallots(3));
        // when
        VotingConfirmation confirmationCode = voting.vote((Ballot b) -> {
            b.check("Bill");
        });
        // then
        assertThat(confirmationCode.getCode()).isEqualTo(voting.ballotsInBox.get(0).getConfirmationCode());
    }
}