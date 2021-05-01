using NUnit.Framework;

namespace GitHubAutomation
{
    [TestFixture]
    public class SmokeTests
    {
        private Steps.Steps steps = new Steps.Steps();
        private const string USERNAME = "some_username";
        private const string PASSWORD = "some_password";
        private const int REPOSITORY_RANDOM_POSTFIX_LENGTH = 6;

        [SetUp]
        public void Init()
        {
            steps.InitBrowser();
        }

        [TearDown]
        public void Cleanup()
        {
            steps.CloseBrowser();
        }

        [Test]
        public void OneCanLoginGithub()
        {
            steps.LoginGithub(USERNAME, PASSWORD);
            Assert.AreEqual(USERNAME, steps.GetLoggedInUserName());
        }

        [Test]
        public void OneCanCreateProject()
        {
            steps.LoginGithub(USERNAME, PASSWORD);
            string repositoryName = steps.GenerateRandomRepositoryNameWithCharLength(REPOSITORY_RANDOM_POSTFIX_LENGTH);
            steps.CreateNewRepository(repositoryName, "auto-generated test repo");
            Assert.AreEqual(repositoryName, steps.GetCurrentRepositoryName());
        }
    }
}
