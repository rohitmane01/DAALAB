import pandas as pd
import matplotlib.pyplot as plt
import sys
import os

# Get CSV filename from command line
if len(sys.argv) < 2:
    print("Usage: python graphPloting.py <csv_file>")
    sys.exit(1)

csv_file = sys.argv[1]

# Load CSV
df = pd.read_csv(csv_file)

# Check structure
expected_cols = {"InputSize", "KeyPosition", "BinarySearchTime(ns)", "LinearSearchTime(ns)"}
if not expected_cols.issubset(df.columns):
    print("Error: CSV format mismatch. Expected columns:", expected_cols)
    sys.exit(1)

# Group by input size and key position, then average if multiple runs
grouped = df.groupby(["InputSize", "KeyPosition"]).mean().reset_index()

def plot_graph(log_scale=False):
    plt.figure()

    # Plot: Binary Search
    for pos in ["Start", "Middle", "End"]:
        subset = grouped[grouped["KeyPosition"] == pos]
        plt.plot(subset["InputSize"], subset["BinarySearchTime(ns)"], marker='o', label=f"Binary ({pos})")

    # Plot: Linear Search
    for pos in ["Start", "Middle", "End"]:
        subset = grouped[grouped["KeyPosition"] == pos]
        plt.plot(subset["InputSize"], subset["LinearSearchTime(ns)"], marker='o', linestyle="--", label=f"Linear ({pos})")

    # Labels and title
    plt.xlabel("Input Size (n)")
    plt.ylabel("Time (ns)")
    title = "Binary vs Linear Search Performance"
    if log_scale:
        plt.yscale("log")
        title += " (Log Scale)"
    plt.title(title)
    plt.legend()
    plt.grid(True, which="both", linestyle="--", linewidth=0.5)

    # Save PNG with same name as CSV
    suffix = "_log" if log_scale else ""
    png_file = os.path.splitext(csv_file)[0] + f"{suffix}.png"
    plt.savefig(png_file, dpi=300)
    plt.show()
    print(f"Plot saved as {png_file}")

# Plot normal scale
plot_graph(log_scale=False)

# Plot log scale
plot_graph(log_scale=True)
