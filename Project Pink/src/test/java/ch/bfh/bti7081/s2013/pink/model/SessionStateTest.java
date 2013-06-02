package ch.bfh.bti7081.s2013.pink.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * See /doc/SessionState.jpg
 * 
 * @author Christian Meyer <chrigu.meyer@gmail.com>
 */
public class SessionStateTest {
	@Test
	public void testNewSession() throws Exception {
		Session session = getSession();
		assertEquals(SessionState.PLANNED, session.getSessionState());
	}

	// Planned
	@Test
	public void testSessionPlannedAbortedNOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.ABORTED, true);
	}

	@Test
	public void testSessionPlannedArchivedNOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.ARCHIVED, true);
	}

	@Test
	public void testSessionPlannedCancelledOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.CANCELLED, false);
	}

	@Test
	public void testSessionPlannedFinishedNOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.FINISHED, true);
	}

	@Test
	public void testSessionPlannedInvalidNOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.INVALID, true);
	}

	@Test
	public void testSessionPlannedPlannedNOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.PLANNED, true);
	}

	@Test
	public void testSessionPlannedReopenedNOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.REOPENED, true);
	}

	@Test
	public void testSessionPlannedStartedOK() throws Exception {
		testSession(SessionState.PLANNED, SessionState.STARTED, false);
	}

	// Started
	@Test
	public void testSessionStartedAbortedOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.ABORTED, false);
	}

	@Test
	public void testSessionStartedArchivedNOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.ARCHIVED, true);
	}

	@Test
	public void testSessionStartedCancelledNOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.CANCELLED, true);
	}

	@Test
	public void testSessionStartedFinishedOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.FINISHED, false);
	}

	@Test
	public void testSessionStartedInvalidNOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.INVALID, true);
	}

	@Test
	public void testSessionStartedPlannedNOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.PLANNED, true);
	}

	@Test
	public void testSessionStartedReopenedNOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.REOPENED, true);
	}

	@Test
	public void testSessionStartedStartedNOK() throws Exception {
		testSession(SessionState.STARTED, SessionState.STARTED, true);
	}

	// Aborted
	@Test
	public void testSessionAbortedAbortedNOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.ABORTED, true);
	}

	@Test
	public void testSessionAbortedArchivedOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.ARCHIVED, false);
	}

	@Test
	public void testSessionAbortedCancelledNOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.CANCELLED, true);
	}

	@Test
	public void testSessionAbortedFinishedNOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.FINISHED, true);
	}

	@Test
	public void testSessionAbortedInvalidNOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.INVALID, true);
	}

	@Test
	public void testSessionAbortedPlannedOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.PLANNED, false);
	}

	@Test
	public void testSessionAbortedReopenedNOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.REOPENED, true);
	}

	@Test
	public void testSessionAbortedStartedOK() throws Exception {
		testSession(SessionState.ABORTED, SessionState.STARTED, false);
	}

	// Cancelled
	@Test
	public void testSessionCancelledAbortedNOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.ABORTED, true);
	}

	@Test
	public void testSessionCancelledArchivedOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.ARCHIVED, false);
	}

	@Test
	public void testSessionCancelledCancelledNOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.CANCELLED, true);
	}

	@Test
	public void testSessionCancelledFinishedNOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.FINISHED, true);
	}

	@Test
	public void testSessionCancelledInvalidNOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.INVALID, true);
	}

	@Test
	public void testSessionCancelledPlannedNOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.PLANNED, true);
	}

	@Test
	public void testSessionCancelledReopenedNOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.REOPENED, true);
	}

	@Test
	public void testSessionCancelledStartedNOK() throws Exception {
		testSession(SessionState.CANCELLED, SessionState.STARTED, true);
	}

	// Finished
	@Test
	public void testSessionFinishedAbortedNOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.ABORTED, true);
	}

	@Test
	public void testSessionFinishedArchivedOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.ARCHIVED, false);
	}

	@Test
	public void testSessionFinishedCancelledNOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.CANCELLED, true);
	}

	@Test
	public void testSessionFinishedFinishedNOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.FINISHED, true);
	}

	@Test
	public void testSessionFinishedInvalidNOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.INVALID, true);
	}

	@Test
	public void testSessionFinishedPlannedNOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.PLANNED, true);
	}

	@Test
	public void testSessionFinishedReopenedNOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.REOPENED, true);
	}

	@Test
	public void testSessionFinishedStartedOK() throws Exception {
		testSession(SessionState.FINISHED, SessionState.STARTED, false);
	}

	// Archived
	@Test
	public void testSessionArchivedAbortedNOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.ABORTED, true);
	}

	@Test
	public void testSessionArchivedArchivedNOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.ARCHIVED, true);
	}

	@Test
	public void testSessionArchivedCancelledNOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.CANCELLED, true);
	}

	@Test
	public void testSessionArchivedFinishedNOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.FINISHED, true);
	}

	@Test
	public void testSessionArchivedInvalidNOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.INVALID, true);
	}

	@Test
	public void testSessionArchivedPlannedNOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.PLANNED, true);
	}

	@Test
	public void testSessionArchivedReopenedOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.REOPENED, false);
	}

	@Test
	public void testSessionArchivedStartedNOK() throws Exception {
		testSession(SessionState.ARCHIVED, SessionState.STARTED, true);
	}

	// Reopened
	@Test
	public void testSessionReopenedAbortedNOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.ABORTED, true);
	}

	@Test
	public void testSessionReopenedArchivedOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.ARCHIVED, false);
	}

	@Test
	public void testSessionReopenedCancelledNOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.CANCELLED, true);
	}

	@Test
	public void testSessionReopenedFinishedNOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.FINISHED, true);
	}

	@Test
	public void testSessionReopenedInvalidNOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.INVALID, true);
	}

	@Test
	public void testSessionReopenedPlannedNOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.PLANNED, true);
	}

	@Test
	public void testSessionReopenedReopenedNOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.REOPENED, true);
	}

	@Test
	public void testSessionReopenedStartedNOK() throws Exception {
		testSession(SessionState.REOPENED, SessionState.STARTED, true);
	}

	private void testSession(SessionState start, SessionState end,
			boolean expectFail) throws IllegalStateException {
		Session session = getSession(start);
		session.changeState(end);

		if (expectFail)
			assertEquals(start, session.getSessionState());
		else
			assertEquals(end, session.getSessionState());
	}

	private Session getSession() {
		Patient patient = TestDataSource.getPatient();
		Doctor doctor = TestDataSource.getDoctor();
		return new Session(patient, doctor);
	}

	private Session getSession(SessionState state) {
		Session session = getSession();
		try {
			switch (state) {
			case PLANNED:
				break;
			case STARTED:
				session.changeState(SessionState.STARTED);
				break;
			case ABORTED:
				session.changeState(SessionState.STARTED);
				session.changeState(SessionState.ABORTED);
				break;
			case CANCELLED:
				session.changeState(SessionState.CANCELLED);
				break;
			case FINISHED:
				session.changeState(SessionState.STARTED);
				session.changeState(SessionState.FINISHED);
				break;
			case ARCHIVED:
				session.changeState(SessionState.CANCELLED);
				session.changeState(SessionState.ARCHIVED);
				break;
			case REOPENED:
				session.changeState(SessionState.CANCELLED);
				session.changeState(SessionState.ARCHIVED);
				session.changeState(SessionState.REOPENED);
				break;
			case INVALID:
			default:
				fail("State " + state + " can't be reached.");
			}
		} catch (IllegalStateException e) {
			fail("Failed setting state - error in test");
		}
		return session;
	}
}
