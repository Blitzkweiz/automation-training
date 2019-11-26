using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using OpenQA.Selenium.Support.UI;
using Selenium.Page;
using SeleniumExtras.PageObjects;

namespace Selenium
{
    [TestFixture]
    class HappyToursUsaTests
    {
        public IWebDriver Driver { get; set; }
        public WebDriverWait Wait { get; set; }


        [SetUp]
        public void SetupTest()
        {
            this.Driver = new ChromeDriver();
            this.Driver.Manage().Window.Maximize();
            this.Wait = new WebDriverWait(this.Driver, TimeSpan.FromSeconds(30));
        }

       [TearDown]
        public void TeardownTest()
        {
            this.Driver.Quit();
        }

        [Test]
        public void SmokeTest()
        {
            HomePage homePage = new HomePage(Driver);
            homePage.OpenPage();
            homePage.ChoosePickUpDate();
            homePage.ChoosePickUpTime();
            homePage.ChoosePickUpDestination();
            homePage.ChoosePickUpLocation();
            homePage.ChooseDropOffTime();
            homePage.ChooseDropOffDestination();
            homePage.ChooseDropOffLocation();
            Wait.Timeout = TimeSpan.FromMinutes(1);
        }
    }
}